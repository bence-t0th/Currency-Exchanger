import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.10"
}
group = "me.tothbence"
version = "1.0-SNAPSHOT"
val ktor_version = "1.4.0"
val retrofit_version = "2.1.0"

repositories {
    mavenCentral()
    jcenter()
}
dependencies {
    testImplementation(kotlin("test-junit"))
    implementation ("io.ktor:ktor-server-netty:$ktor_version")
    implementation ("com.squareup.retrofit2:retrofit:$retrofit_version")
    implementation("com.squareup.moshi:moshi:1.11.0")
    implementation ("com.squareup.retrofit2:converter-moshi:2.4.0")
    implementation("com.squareup.retrofit2:converter-gson:2.1.0")
}
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
