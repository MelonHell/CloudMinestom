plugins {
    java
    `java-library`
    `maven-publish`
}

group = "ru.melonhell.cloudminestom"
version = "1.0.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://jitpack.io/")
}

dependencies {
    api(libs.cloud.core)
    compileOnly(libs.minestom)
}

java.withSourcesJar()
publishing {
    publications {
        create<MavenPublication>("maven") {
            artifactId = rootProject.name
            from(components["java"])
        }
    }

    repositories {
        mavenLocal()
        maven {
            url = uri("https://maven.melonhell.ru/public/")
            credentials {
                username = findProperty("MELONHELL_REPO_USR")?.toString()
                password = findProperty("MELONHELL_REPO_PSW")?.toString()
            }
        }
    }
}