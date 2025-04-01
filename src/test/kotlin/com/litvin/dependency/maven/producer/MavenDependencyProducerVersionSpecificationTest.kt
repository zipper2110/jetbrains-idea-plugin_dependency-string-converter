package com.litvin.dependency.maven.producer

import com.litvin.dependency.converter.producer.MavenDependencyProducer
import com.litvin.dependency.model.DependencyModel
import com.litvin.dependency.maven.reference.MavenDimension2VersionSpecification
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

/**
 * Tests that validate producing of Maven coordinates with various version specifications 
 * using MavenDependencyProducer
 */
class MavenDependencyProducerVersionSpecificationTest {
    private val producer = MavenDependencyProducer()
    
    @Test
    fun testProduceExactVersion() {
        val dependency = DependencyModel(
            groupId = "org.springframework",
            artifactId = "spring-core",
            version = "5.3.9"
        )
        val result = producer.produce(dependency)
        assertEquals(normalizeXml(MavenDimension2VersionSpecification.exactVersionXml), normalizeXml(result))
    }
    
    @Test
    fun testProduceSnapshotVersion() {
        val dependency = DependencyModel(
            groupId = "org.example",
            artifactId = "my-library",
            version = "1.0.0-SNAPSHOT"
        )
        val result = producer.produce(dependency)
        assertEquals(normalizeXml(MavenDimension2VersionSpecification.snapshotVersionXml), normalizeXml(result))
    }
    
    @Test
    fun testProduceQualifierVersion() {
        val dependency = DependencyModel(
            groupId = "org.apache.tomcat",
            artifactId = "tomcat-catalina",
            version = "9.0.56-beta"
        )
        val result = producer.produce(dependency)
        assertEquals(normalizeXml(MavenDimension2VersionSpecification.qualifierVersionXml), normalizeXml(result))
    }
    
    @Test
    fun testProduceTimestampVersion() {
        val dependency = DependencyModel(
            groupId = "org.example",
            artifactId = "my-library",
            version = "1.0.0-20220131.131415-42"
        )
        val result = producer.produce(dependency)
        assertEquals(normalizeXml(MavenDimension2VersionSpecification.timestampVersionXml), normalizeXml(result))
    }
    
    @Test
    fun testProduceVersionRangeInclusiveExclusive() {
        val dependency = DependencyModel(
            groupId = "org.example",
            artifactId = "my-library",
            version = "[1.0.0,2.0.0)"
        )
        val result = producer.produce(dependency)
        assertEquals(normalizeXml(MavenDimension2VersionSpecification.versionRangeInclusiveExclusiveXml), normalizeXml(result))
    }
    
    @Test
    fun testProduceVersionRangeInclusiveInclusive() {
        val dependency = DependencyModel(
            groupId = "org.example",
            artifactId = "my-library",
            version = "[1.0.0,2.0.0]"
        )
        val result = producer.produce(dependency)
        assertEquals(normalizeXml(MavenDimension2VersionSpecification.versionRangeInclusiveInclusiveXml), normalizeXml(result))
    }
    
    @Test
    fun testProduceVersionRangeExclusiveInclusive() {
        val dependency = DependencyModel(
            groupId = "org.example",
            artifactId = "my-library",
            version = "(1.0.0,2.0.0]"
        )
        val result = producer.produce(dependency)
        assertEquals(normalizeXml(MavenDimension2VersionSpecification.versionRangeExclusiveInclusiveXml), normalizeXml(result))
    }
    
    @Test
    fun testProduceVersionRangeGreaterThanOrEqual() {
        val dependency = DependencyModel(
            groupId = "org.example",
            artifactId = "my-library",
            version = "[1.0.0,)"
        )
        val result = producer.produce(dependency)
        assertEquals(normalizeXml(MavenDimension2VersionSpecification.versionRangeGreaterThanOrEqualXml), normalizeXml(result))
    }
    
    @Test
    fun testProduceVersionRangeLessThanOrEqual() {
        val dependency = DependencyModel(
            groupId = "org.example",
            artifactId = "my-library",
            version = "(,2.0.0]"
        )
        val result = producer.produce(dependency)
        assertEquals(normalizeXml(MavenDimension2VersionSpecification.versionRangeLessThanOrEqualXml), normalizeXml(result))
    }
    
    @Test
    fun testProduceLatestVersion() {
        val dependency = DependencyModel(
            groupId = "org.example",
            artifactId = "my-library",
            version = "LATEST"
        )
        val result = producer.produce(dependency)
        assertEquals(normalizeXml(MavenDimension2VersionSpecification.latestVersionXml), normalizeXml(result))
    }
    
    @Test
    fun testProduceReleaseVersion() {
        val dependency = DependencyModel(
            groupId = "org.example",
            artifactId = "my-library",
            version = "RELEASE"
        )
        val result = producer.produce(dependency)
        assertEquals(normalizeXml(MavenDimension2VersionSpecification.releaseVersionXml), normalizeXml(result))
    }
    
    /**
     * Normalizes XML by removing whitespace between tags to make comparison easier
     */
    private fun normalizeXml(xml: String): String {
        return xml.replace(Regex("\\s+"), " ").trim()
    }
} 