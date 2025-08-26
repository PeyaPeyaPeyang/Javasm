import org.jetbrains.changelog.markdownToHTML
import org.jetbrains.intellij.platform.gradle.IntelliJPlatformType
import org.jetbrains.intellij.platform.gradle.models.ProductRelease
import java.nio.file.Files

plugins {
    id("java")
    id("org.jetbrains.intellij.platform") version "2.5.0"
    id("org.jetbrains.changelog") version "2.2.0"
}

group = "tokyo.peya.plugin"
version = "1.0.0"

repositories {
    mavenCentral()
    mavenLocal()
    intellijPlatform {
        defaultRepositories()
    }
}

// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-intellij-platform-gradle-plugin.html
dependencies {
    intellijPlatform {
        create("IC", "2025.1")
        testFramework(org.jetbrains.intellij.platform.gradle.TestFrameworkType.Platform)

        // Add necessary plugin dependencies for compilation here, example:
        bundledPlugin("com.intellij.java")
    }

    compileOnly("org.projectlombok:lombok:1.18.38")
    annotationProcessor("org.projectlombok:lombok:1.18.38")

    implementation("org.ow2.asm:asm:9.8")
    implementation("org.ow2.asm:asm-commons:9.8")
    implementation("org.ow2.asm:asm-util:9.8")

    implementation(project(":javasm-jps-plugin"))
    implementation("tokyo.peya:langjal:1.2.1")
    implementation("org.antlr:antlr4-intellij-adaptor:0.1")
}

fun extractLatestChangeNotes(): String {
    val projectRoot = project.rootDir.toPath()
    val changelogPath = projectRoot.resolve("CHANGELOG.md")
    val content = Files.readString(changelogPath)

    val regex = Regex("## \\[.*?] - .*?\n(.*?)(?=\n## |\\Z)", RegexOption.DOT_MATCHES_ALL)
    val match = regex.find(content)

    return match?.groups?.get(1)?.value ?: "No changelog available."
}


intellijPlatform {
    pluginConfiguration {
        ideaVersion {
            sinceBuild = "251"
        }

        description.set(providers.fileContents(layout.projectDirectory.file("../README.md")).asText.map {
            markdownToHTML(it)
        })
        changeNotes.set(extractLatestChangeNotes())
    }

    pluginVerification {
        ides {
            ide(IntelliJPlatformType.IntellijIdeaCommunity, "2025.1")
            recommended()
            select {
                types = listOf(IntelliJPlatformType.IntellijIdeaCommunity, IntelliJPlatformType.IntellijIdeaUltimate)
                channels = listOf(ProductRelease.Channel.RELEASE)
                sinceBuild = "251"
                untilBuild = "261.*"
            }
        }
    }


    val projectRoot = project.rootDir.toPath()
    val chainPath = projectRoot.resolve("certificates/chain.crt")
    val keyPath = projectRoot.resolve("certificates/private.pem")
    val passPath = projectRoot.resolve("certificates/password.txt")

    if (Files.exists(chainPath) && Files.exists(keyPath) && Files.exists(passPath)) {
        signing {
            certificateChain.set(Files.readString(chainPath))
            privateKey.set(Files.readString(keyPath))
            password.set(Files.readString(passPath))
        }
    }
}
tasks {
    // Set the JVM compatibility versions
    withType<JavaCompile> {
        sourceCompatibility = "21"
        targetCompatibility = "21"
    }
}

sourceSets {
    main {
        java {
            srcDirs("src/main/java", "src/main/gen")
        }
    }
}
