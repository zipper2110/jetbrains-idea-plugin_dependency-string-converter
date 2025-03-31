package com.litvin.dependency.converter

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.actionSystem.DataContext
import com.intellij.openapi.actionSystem.ex.AnActionListener
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.EditorFactory
import com.intellij.openapi.editor.actionSystem.EditorActionManager
import com.intellij.openapi.editor.actionSystem.TypedAction
import com.intellij.openapi.editor.actions.PasteAction
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.util.Ref
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.diagnostic.Logger
import java.awt.datatransfer.DataFlavor
import java.awt.Toolkit

class DependencyPasteListener : AnActionListener {
    private val logger = Logger.getInstance(DependencyPasteListener::class.java)

    override fun beforeActionPerformed(action: AnAction, dataContext: DataContext, event: AnActionEvent) {
        if (action is PasteAction) {
            logger.info("Paste action detected")
            
            // Get the editor from the action event
            val editor = event.getData(CommonDataKeys.EDITOR) ?: run {
                logger.warn("No editor found in action event")
                return
            }
            
            // Get the current file being edited
            val file = FileDocumentManager.getInstance().getFile(editor.document) ?: run {
                logger.warn("No file found for editor document")
                return
            }
            val fileName = file.name.lowercase()
            logger.info("Current file: $fileName")
            
            // Check if we're in a Maven or Gradle file
            val inMavenFile = fileName == "pom.xml"
            val inGradleFile = fileName.contains("build.gradle") 
                || fileName.contains("build.gradle.kts") 
                || fileName.contains("build.gradle.clj")
            
            if (!inMavenFile && !inGradleFile) {
                logger.info("Not in a Maven or Gradle file, skipping conversion")
                return
            }
                
            // Get clipboard content
            val clipboard = Toolkit.getDefaultToolkit().systemClipboard
            if (!clipboard.isDataFlavorAvailable(DataFlavor.stringFlavor)) {
                logger.warn("No string data available in clipboard")
                return
            }
            
            val content = try {
                clipboard.getData(DataFlavor.stringFlavor) as String
            } catch (e: Exception) {
                logger.error("Failed to get clipboard content", e)
                return
            }
            
            logger.info("Clipboard content: $content")
            
            // Convert the dependency if needed
            val convertedContent = when {
                inMavenFile && isGradleDependency(content) -> {
                    logger.info("Converting Gradle to Maven dependency")
                    convertGradleToMaven(content)
                }
                inGradleFile && isMavenDependency(content) -> {
                    logger.info("Converting Maven to Gradle dependency")
                    convertMavenToGradle(content, fileName.contains(".kts"))
                }
                else -> {
                    logger.info("No conversion needed")
                    return
                }
            }
            
            logger.info("Converted content: $convertedContent")
            
            // Replace the clipboard content with the converted content
            clipboard.setContents(java.awt.datatransfer.StringSelection(convertedContent), null)
        }
    }
    
    fun isGradleDependency(content: String): Boolean {
        // Split by newlines and find the first non-comment line
        val lines = content.lines()
        val dependencyLine = lines.firstOrNull { !it.trim().startsWith("//") }?.trim() ?: return false
        
        val isGradle = dependencyLine.matches(""".*(?:implementation|api|testImplementation|runtimeOnly|compileOnly)\s*\(\s*['"](.+):(.+):(.+)(?::(.+))?(?:@(.+))?['"].*""".toRegex()) ||
                      dependencyLine.matches(""".*(?:implementation|api|testImplementation|runtimeOnly|compileOnly)\s*\(\s*['"](.+):(.+):(.+)(?::(.+))?(?:@(.+))?['"]\s*\)\s*\{.*exclude.*\}.*""".toRegex())
        logger.info("Is Gradle dependency: $isGradle, line: $dependencyLine")
        return isGradle
    }
    
    private fun isMavenDependency(content: String): Boolean {
        val isMaven = content.contains("<dependency>") && 
               content.contains("<groupId>") && 
               content.contains("<artifactId>") && 
               content.contains("<version>")
        logger.info("Is Maven dependency: $isMaven")
        return isMaven
    }
    
    fun convertGradleToMaven(gradleDependency: String): String {
        // Extract group, artifact, version, classifier, type from Gradle format
        val regex = """.*(?:implementation|api|testImplementation|runtimeOnly|compileOnly)\s*\(\s*['"]([^:]+):([^:]+):([^:@'"]+)(?::([^@'"]+))?(?:@([^'"]+))?['"].*""".toRegex()
        val matchResult = regex.find(gradleDependency) ?: return gradleDependency
        
        val (group, artifact, version, classifier, type) = matchResult.destructured
        
        // Extract exclusions if present
        val exclusions = mutableListOf<String>()
        // Match both Groovy and Kotlin DSL exclusion formats
        val exclusionRegex = """exclude\s+(?:group:\s*['"]([^'"]+)['"],\s*module:\s*['"]([^"']+)['"]|group\s*=\s*["']([^"']+)["'],\s*module\s*=\s*["']([^"']+)["'])""".toRegex()
        exclusionRegex.findAll(gradleDependency).forEach { match ->
            val (groovyGroup, groovyModule, kotlinGroup, kotlinModule) = match.destructured
            val excludeGroup = groovyGroup.takeIf { it.isNotEmpty() } ?: kotlinGroup
            val excludeModule = groovyModule.takeIf { it.isNotEmpty() } ?: kotlinModule
            exclusions.add("""        <exclusion>
            <groupId>$excludeGroup</groupId>
            <artifactId>$excludeModule</artifactId>
        </exclusion>""")
        }

        val scope = when {
            gradleDependency.contains("testImplementation") -> "test"
            gradleDependency.contains("runtimeOnly") -> "runtime"
            gradleDependency.contains("compileOnly") -> "provided"
            exclusions.isNotEmpty() && gradleDependency.contains("implementation") -> "compile"
            else -> null
        }
        
        // Build Maven XML
        val exclusionsBlock = if (exclusions.isNotEmpty()) """
    <exclusions>
${exclusions.joinToString("\n")}
    </exclusions>""" else ""
        
        val classifierBlock = if (classifier.isNotEmpty()) """
    <classifier>$classifier</classifier>""" else ""
        
        val typeBlock = if (type.isNotEmpty()) """
    <type>$type</type>""" else ""
        
        val scopeBlock = if (scope != null) """
    <scope>$scope</scope>""" else ""
        
        return """<dependency>
    <groupId>$group</groupId>
    <artifactId>$artifact</artifactId>
    <version>$version</version>$classifierBlock$typeBlock$scopeBlock$exclusionsBlock
</dependency>"""
    }
    
    fun convertMavenToGradle(mavenDependency: String, isKotlinDSL: Boolean): String {
        // Extract components from Maven XML
        val groupIdRegex = "<groupId>([^<]+)</groupId>".toRegex()
        val artifactIdRegex = "<artifactId>([^<]+)</artifactId>".toRegex()
        val versionRegex = "<version>([^<]+)</version>".toRegex()
        val scopeRegex = "<scope>([^<]+)</scope>".toRegex()
        val classifierRegex = "<classifier>([^<]+)</classifier>".toRegex()
        val typeRegex = "<type>([^<]+)</type>".toRegex()
        
        val groupIdMatch = groupIdRegex.find(mavenDependency)
        val artifactIdMatch = artifactIdRegex.find(mavenDependency)
        val versionMatch = versionRegex.find(mavenDependency)
        
        if (groupIdMatch == null || artifactIdMatch == null || versionMatch == null) {
            return mavenDependency
        }
        
        val groupId = groupIdMatch.groupValues[1]
        val artifactId = artifactIdMatch.groupValues[1]
        val version = versionMatch.groupValues[1]
        
        // Get classifier and type if present
        val classifierMatch = classifierRegex.find(mavenDependency)
        val typeMatch = typeRegex.find(mavenDependency)
        val classifier = classifierMatch?.groupValues?.get(1) ?: ""
        val type = typeMatch?.groupValues?.get(1) ?: ""
        
        // Determine configuration based on scope
        val scopeMatch = scopeRegex.find(mavenDependency)
        val scope = scopeMatch?.groupValues?.get(1) ?: "compile"
        
        val configuration = when (scope) {
            "test" -> "testImplementation"
            "runtime" -> "runtimeOnly"
            "provided" -> "compileOnly"
            else -> "implementation"
        }
        
        // Get comment if it exists
        val commentRegex = "<!-- (.+) -->".toRegex()
        val commentMatch = commentRegex.find(mavenDependency)
        val comment = commentMatch?.groupValues?.get(1) ?: ""
        
        // Extract exclusions if present
        val exclusions = mutableListOf<String>()
        val exclusionRegex = """<exclusion>\s*<groupId>([^<]+)</groupId>\s*<artifactId>([^<]+)</artifactId>\s*</exclusion>""".toRegex()
        exclusionRegex.findAll(mavenDependency).forEach { match ->
            val (excludeGroup, excludeModule) = match.destructured
            exclusions.add(if (isKotlinDSL) 
                "exclude(group = \"$excludeGroup\", module = \"$excludeModule\")"
            else 
                "exclude group: '$excludeGroup', module: '$excludeModule'"
            )
        }
        
        // Format for Gradle
        val quoteMark = if (isKotlinDSL) "\"" else "'"
        val commentLine = if (comment.isNotEmpty()) "// $comment\n" else ""
        
        val classifierPart = if (classifier.isNotEmpty()) ":$classifier" else ""
        val typePart = if (type.isNotEmpty()) "@$type" else ""
        val dependencyLine = "$configuration($quoteMark$groupId:$artifactId:$version$classifierPart$typePart$quoteMark)"
        val exclusionsBlock = if (exclusions.isNotEmpty()) " {\n    ${exclusions.joinToString("\n    ")}\n}" else ""
        
        return "$commentLine$dependencyLine$exclusionsBlock"
    }
} 