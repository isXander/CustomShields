plugins {
    val kotlinVersion: String by System.getProperties()

    kotlin("jvm") version kotlinVersion
    id("fabric-loom") version "0.10.+"
    java
}

group = "dev.isxander"
version = "1.0"

repositories {
    mavenCentral()
    maven("https://jitpack.io/")
}

dependencies {
    val kotlinVersion: String by System.getProperties()
    val minecraftVersion: String by project
    val yarnVersion: String by project
    val loaderVersion: String by project
    val fabricVersion: String by project
    val fabricKotlinVersion: String by project
    val kambrikVersion: String by project
    val shieldLibVersion: String by project

    implementation(kotlin("stdlib-jdk8", kotlinVersion))

    minecraft("com.mojang:minecraft:$minecraftVersion")
    mappings("net.fabricmc:yarn:$yarnVersion:v2")
    modImplementation("net.fabricmc:fabric-loader:$loaderVersion")
    modImplementation("net.fabricmc.fabric-api:fabric-api:$fabricVersion")
    modImplementation("net.fabricmc:fabric-language-kotlin:$fabricKotlinVersion+kotlin.$kotlinVersion")
    modImplementation("io.ejekta:kambrik:$kambrikVersion")
    modImplementation("com.github.CrimsonDawn45:Fabric-Shield-Lib:$shieldLibVersion")
    include("com.github.CrimsonDawn45:Fabric-Shield-Lib:$shieldLibVersion")
}

kotlin {
    jvmToolchain {
        (this as JavaToolchainSpec).languageVersion.set(JavaLanguageVersion.of(17))
    }
}

tasks {
    processResources {
        inputs.property("version", project.version)
        filesMatching("fabric.mod.json") {
            expand(
                mutableMapOf(
                    "version" to project.version
                )
            )
        }
    }
}
