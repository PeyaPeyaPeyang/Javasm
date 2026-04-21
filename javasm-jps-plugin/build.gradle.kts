plugins {
    id("java")
}

group = "tokyo.peya.plugin"
version = "1.1.1"

repositories {
    mavenCentral()
    mavenLocal()
    maven("https://www.jetbrains.com/intellij-repository/releases")
    maven("https://www.jetbrains.com/intellij-repository/snapshots")
    maven("https://packages.jetbrains.team/maven/p/ij/intellij-dependencies")
}

dependencies {
    compileOnly("com.jetbrains.intellij.platform:core:261.+")
    compileOnly("com.jetbrains.intellij.platform:jps-build:261.+")
    compileOnly("com.jetbrains.intellij.platform:jps-model:261.+")
    compileOnly("com.jetbrains.intellij.platform:util:261.+")
    compileOnly("com.jetbrains.intellij.tools:jps-build-standalone:261.+")

    compileOnly("org.projectlombok:lombok:1.18.44")
    annotationProcessor("org.projectlombok:lombok:1.18.44")
    compileOnly("org.jetbrains:annotations:26.0.2")
    annotationProcessor("org.jetbrains:annotations:26.0.2")

    implementation("org.ow2.asm:asm:9.9.1")
    implementation("org.ow2.asm:asm-commons:9.9.1")
    implementation("org.ow2.asm:asm-util:9.9.1")

    implementation("com.google.guava:guava:33.4.8-jre")
    implementation("tokyo.peya:langjal:1.2.1")
}

tasks {
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
