plugins {
    alias(libs.plugins.kotlin)
    alias(libs.plugins.serialization)
    alias(libs.plugins.detekt)
    alias(libs.plugins.ktlint)
    alias(libs.plugins.vanniktech.maven.publish)
}

group = "ru.kode.android"
version = "1.0.0"

kotlin {
    jvmToolchain(17)
}

dependencies {
    compileOnly(gradleApi())
    compileOnly(localGroovy())
    compileOnly(libs.agp)

    implementation(libs.okhttp)
    implementation(libs.okhttpLogging)
    implementation(libs.retrofit)
    implementation(libs.serializationJson)

    testImplementation(gradleApi())
    testImplementation(localGroovy())
    testImplementation(platform(libs.junitBom))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation(libs.okhttpMockWebServer)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
    // Gradle's ProjectBuilder needs deep reflection into the JDK on Java 16+.
    jvmArgs(
        "--add-opens=java.base/java.lang=ALL-UNNAMED",
        "--add-opens=java.base/java.util=ALL-UNNAMED",
        "--add-opens=java.base/java.lang.invoke=ALL-UNNAMED",
    )
}

ktlint {
    verbose.set(true)
    outputToConsole.set(true)
    enableExperimentalRules.set(true)
    android.set(false)
    ignoreFailures.set(false)
    disabledRules.set(setOf("import-ordering"))
    filter {
        include("src/main/**/*.kt")
    }
}

detekt {
    source.setFrom(files("src/main/kotlin"))
    buildUponDefaultConfig = true
}

mavenPublishing {
    coordinates(groupId = "ru.kode.android", artifactId = "gradle-plugin-commons", version = version.toString())

    publishToMavenCentral()
    signAllPublications()

    pom {
        name.set("Gradle Plugin Commons")
        description.set("Shared generic utilities (logger service, Groovy DSL config helper) for appKODE Gradle plugins")
        inceptionYear.set("2026")
        url.set("https://github.com/appKODE/gradle-plugin-commons")

        licenses {
            license {
                name.set("The Apache License, Version 2.0")
                url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
            }
        }

        developers {
            developer {
                id.set("dmitrii.suzdalev.dz")
                name.set("DIma")
                email.set("dz@kode.ru")
            }
        }

        scm {
            url.set("https://github.com/appKODE/gradle-plugin-commons")
            connection.set("scm:git:https://github.com/appKODE/gradle-plugin-commons.git")
            developerConnection.set("scm:git:ssh://git@github.com:appKODE/gradle-plugin-commons.git")
        }
    }
}
