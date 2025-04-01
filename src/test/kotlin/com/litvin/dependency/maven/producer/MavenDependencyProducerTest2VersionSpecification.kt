package com.litvin.dependency.maven.producer

import com.litvin.dependency.converter.producer.MavenDependencyProducer
import com.litvin.dependency.model.DependencyModel
import com.litvin.dependency.maven.reference.MavenParserTest2VersionSpecification
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import com.litvin.dependency.util.XmlTestUtils.normalizeXml

/**
 * Tests that validate producing of Maven coordinates with various version specifications 
 * using MavenDependencyProducer
 */
class MavenDependencyProducerTest2VersionSpecification {
    private val producer = MavenDependencyProducer()
    
    @Test
    fun testProduceExactVersion() {
        val dependency = DependencyModel(
            groupId = "org.springframework",
            artifactId = "spring-core",
            version = "5.3.9"
        )
        val result = producer.produce(dependency)
        assertEquals(normalizeXml(MavenParserTest2VersionSpecification.exactVersionXml), normalizeXml(result))
    }
    
    @Test
    fun testProduceSnapshotVersion() {
        val dependency = DependencyModel(
            groupId = "org.example",
            artifactId = "my-library",
            version = "1.0.0-SNAPSHOT"
        )
        val result = producer.produce(dependency)
        assertEquals(normalizeXml(MavenParserTest2VersionSpecification.snapshotVersionXml), normalizeXml(result))
    }
    
    @Test
    fun testProduceQualifierVersion() {
        val dependency = DependencyModel(
            groupId = "org.apache.tomcat",
            artifactId = "tomcat-catalina",
            version = "9.0.56-beta"
        )
        val result = producer.produce(dependency)
        assertEquals(normalizeXml(MavenParserTest2VersionSpecification.qualifierVersionXml), normalizeXml(result))
    }
    
    @Test
    fun testProduceTimestampVersion() {
        val dependency = DependencyModel(
            groupId = "org.example",
            artifactId = "my-library",
            version = "1.0.0-20220131.131415-42"
        )
        val result = producer.produce(dependency)
        assertEquals(normalizeXml(MavenParserTest2VersionSpecification.timestampVersionXml), normalizeXml(result))
    }
    
    @Test
    fun testProduceVersionRangeInclusiveExclusive() {
        val dependency = DependencyModel(
            groupId = "org.example",
            artifactId = "my-library",
            version = "[1.0.0,2.0.0)"
        )
        val result = producer.produce(dependency)
        assertEquals(normalizeXml(MavenParserTest2VersionSpecification.versionRangeInclusiveExclusiveXml), normalizeXml(result))
    }
    
    @Test
    fun testProduceVersionRangeInclusiveInclusive() {
        val dependency = DependencyModel(
            groupId = "org.example",
            artifactId = "my-library",
            version = "[1.0.0,2.0.0]"
        )
        val result = producer.produce(dependency)
        assertEquals(normalizeXml(MavenParserTest2VersionSpecification.versionRangeInclusiveInclusiveXml), normalizeXml(result))
    }
    
    @Test
    fun testProduceVersionRangeExclusiveInclusive() {
        val dependency = DependencyModel(
            groupId = "org.example",
            artifactId = "my-library",
            version = "(1.0.0,2.0.0]"
        )
        val result = producer.produce(dependency)
        assertEquals(normalizeXml(MavenParserTest2VersionSpecification.versionRangeExclusiveInclusiveXml), normalizeXml(result))
    }
    
    @Test
    fun testProduceVersionRangeGreaterThanOrEqual() {
        val dependency = DependencyModel(
            groupId = "org.example",
            artifactId = "my-library",
            version = "[1.0.0,)"
        )
        val result = producer.produce(dependency)
        assertEquals(normalizeXml(MavenParserTest2VersionSpecification.versionRangeGreaterThanOrEqualXml), normalizeXml(result))
    }
    
    @Test
    fun testProduceVersionRangeLessThanOrEqual() {
        val dependency = DependencyModel(
            groupId = "org.example",
            artifactId = "my-library",
            version = "(,2.0.0]"
        )
        val result = producer.produce(dependency)
        assertEquals(normalizeXml(MavenParserTest2VersionSpecification.versionRangeLessThanOrEqualXml), normalizeXml(result))
    }
    
    @Test
    fun testProduceLatestVersion() {
        val dependency = DependencyModel(
            groupId = "org.example",
            artifactId = "my-library",
            version = "LATEST"
        )
        val result = producer.produce(dependency)
        assertEquals(normalizeXml(MavenParserTest2VersionSpecification.latestVersionXml), normalizeXml(result))
    }
    
    @Test
    fun testProduceReleaseVersion() {
        val dependency = DependencyModel(
            groupId = "org.example",
            artifactId = "my-library",
            version = "RELEASE"
        )
        val result = producer.produce(dependency)
        assertEquals(normalizeXml(MavenParserTest2VersionSpecification.releaseVersionXml), normalizeXml(result))
    }
} 