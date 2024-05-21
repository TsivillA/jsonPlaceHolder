plugins {
    kotlin("jvm") version "1.9.23"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.junit.jupiter:junit-jupiter-api:5.11.0-M2")

    implementation("io.rest-assured:rest-assured:5.4.0")
    implementation("org.freemarker:freemarker:2.3.32")
    implementation("io.cucumber:cucumber-java:7.18.0")
    implementation("io.cucumber:cucumber-picocontainer:7.18.0")
    implementation("io.qameta.allure:allure-cucumber-jvm:2.20.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.11.0-M2")


    testImplementation("io.cucumber:cucumber-junit-platform-engine:7.18.0")
    testImplementation("org.junit.platform:junit-platform-suite:1.11.0-M2")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}