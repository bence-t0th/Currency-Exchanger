import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.10"
}
group = "me.tothbence"
version = "1.0-SNAPSHOT"
val ktor_version = "1.4.0"

repositories {
    mavenCentral()
    jcenter()
}
dependencies {
    testImplementation(kotlin("test-junit"))
    implementation ("io.ktor:ktor-server-netty:$ktor_version")
}
tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}
