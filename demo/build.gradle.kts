plugins {
    kotlin("jvm") version "1.8.20"
}

repositories {
    mavenCentral()
    maven("https://jitpack.io/")
}

dependencies {
    implementation(rootProject)
    implementation(libs.minestom.ce)
    implementation(libs.cloud.annotations)
}