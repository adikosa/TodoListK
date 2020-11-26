plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    kotlin("plugin.jpa")
}

dependencyManagement {
    imports { mavenBom("org.springframework.boot:spring-boot-dependencies:2.3.5.RELEASE") }
}

dependencies {
    implementation(project(":domain"))

    implementation(kotlin("reflect"))
    implementation(kotlin("stdlib-jdk8"))

    implementation("org.springframework.boot:spring-boot-starter-web")
}
