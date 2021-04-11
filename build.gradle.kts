import net.kyori.indra.sonatypeSnapshots

plugins {
    id("java")
    id("net.kyori.indra") version "1.3.1"
    id("net.kyori.indra.publishing") version "1.3.1"
    id("net.kyori.indra.checkstyle") version "1.3.1"
}

group = "com.gmail.cubitverde"
version = "6.0.0-SNAPSHOT"

repositories {
    mavenCentral()
    sonatypeSnapshots()
    maven("https://repo.codemc.org/repository/maven-public")
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
}

dependencies {
    implementation("org.bstats", "bstats-bukkit", "1.7")
    compileOnly("org.spigotmc", "spigot-api", "1.16.1-R0.1-SNAPSHOT")
}

tasks {

    indra {
        javaVersions {
            target.set(11)
        }
    }

    processResources {
        expand("version" to rootProject.version)
    }

}
