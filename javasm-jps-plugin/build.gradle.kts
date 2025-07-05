plugins {
    id("java")
    id("antlr")
    id("org.jetbrains.intellij.platform") version "2.5.0"
}

group = "tokyo.peya.plugin"
version = "0.0.1"

repositories {
    mavenCentral()
    intellijPlatform {
        defaultRepositories()
    }
}

// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-intellij-platform-gradle-plugin.html
dependencies {
    intellijPlatform {
        create("IC", "2025.1")
        // Add necessary plugin dependencies for compilation here, example:
        bundledPlugin("com.intellij.java")
    }
    compileOnly("org.projectlombok:lombok:1.18.38")
    annotationProcessor("org.projectlombok:lombok:1.18.38")

    antlr("org.antlr:antlr4:4.13.2")
    implementation("org.antlr:antlr4-runtime:4.13.2")

    implementation("org.ow2.asm:asm:9.8")
    implementation("org.ow2.asm:asm-commons:9.8")
    implementation("org.ow2.asm:asm-util:9.8")
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
            srcDirs("src/main/java")
        }
    }
}


tasks.named("runIde") {
    enabled = false
}
tasks.named("prepareSandbox") {
    enabled = false
}
tasks.named("buildSearchableOptions") {
    enabled = false
}
tasks.named("prepareJarSearchableOptions") {
    enabled = false
}
