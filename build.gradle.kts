import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    val kotlinVersion: String by System.getProperties()
    kotlin("jvm") version kotlinVersion
}

group = "net.amond.timemachine"
version = "1.0.0-SNAPSHOT"

repositories {
    jcenter()
}
val kotlinVersion: String by System.getProperties()
val spekVersion: String by System.getProperties()

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    testImplementation ("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly ("org.junit.jupiter:junit-jupiter-engine:5.6.0")

    // spek requires kotlin-reflect, can be omitted if already in the classpath
    testRuntimeOnly ("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
    testImplementation ("org.amshove.kluent:kluent:1.60")

}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = "1.8"
}
tasks.withType<Test> {
    useJUnitPlatform ()
    testLogging {
        events("passed", "skipped", "failed")
    }
}