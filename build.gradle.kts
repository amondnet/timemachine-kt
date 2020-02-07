import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    val kotlinVersion: String by System.getProperties()
    kotlin("jvm") version kotlinVersion
    `maven-publish`
    id("org.jetbrains.dokka") version "0.10.0" }

group = "dev.amond.timemachine"
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
tasks.dokka {
    outputFormat = "html"
    outputDirectory = "$buildDir/javadoc"
}
val dokkaJar by tasks.creating(Jar::class) {
    group = JavaBasePlugin.DOCUMENTATION_GROUP
    description = "Assembles Kotlin docs with Dokka"
    classifier = "javadoc"
    from(tasks.dokka)
}
publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/amondnet/timemachine-kt")
            credentials {
                username = project.findProperty("gpr.user") as String? ?: System.getenv("GITHUB_ACTOR")
                password = project.findProperty("gpr.key") as String? ?: System.getenv("GITHUB_TOKEN")
            }
        }
        maven {
            name = "snapshot"
            url = uri("https://nexus.dietfriends.kr/repository/maven-snapshots/")
            credentials {
                username = project.findProperty("snapshot.user") as String? ?: System.getenv("SNAPSHOT_USERNAME")
                password = project.findProperty("snapshot.password") as String? ?: System.getenv("SNAPSHOT_PASSWORD")
            }
        }
    }
    publications {
        register<MavenPublication>("gpr") {
            from(components["java"])
            artifact(dokkaJar)
        }
    }
}
