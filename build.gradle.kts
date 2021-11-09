import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.31"
    id("io.qameta.allure-aggregate-report") version "2.9.6"
    id("io.qameta.allure-adapter") version "2.9.6"
}

description = "Test automation project"

allprojects {
    apply(plugin = "kotlin")
    apply(plugin = "io.qameta.allure-aggregate-report")
    apply(plugin = "io.qameta.allure-adapter")

    java {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "11"
    }

    repositories {
        mavenCentral()
    }

    dependencies {
        testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    }

    val projectKind = when (project.name) {
        "sub-module-one" -> "one"
        "sub-module-two" -> "two"
        else -> "one"
    }

    val runTestsTask = "runTests${projectKind.toLowerCase().capitalize()}"

    task<Test>(runTestsTask) {
        systemProperties = System.getProperties() as Map<String, *>
        maxParallelForks = 8

        useJUnitPlatform()

        filter {
            includeTestsMatching("com.e2e.$projectKind.*")
        }

        testLogging {
            events("passed", "skipped", "failed", "standard_out")
        }
    }

    val allureVersion: String by project

    allure {
        version.set(allureVersion)
        adapter {
            frameworks {
                junit5
            }
        }
    }
}

val wrapper: Wrapper by tasks
wrapper.gradleVersion = "7.2"
