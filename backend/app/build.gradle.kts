plugins {
    id("org.springframework.boot")

    kotlin("jvm")
    kotlin("plugin.spring")
    kotlin("plugin.jpa")
}

repositories {
    maven("https://repo.spring.io/libs-milestone")
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":storage"))
    implementation(project(":network"))

    implementation(kotlin("reflect"))
    implementation(kotlin("stdlib-jdk8"))

//    testImplementation("org.springframework.boot:spring-boot-starter-test") {
//        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
//    }

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    implementation("org.springframework.boot:spring-boot-starter-security")
    testImplementation("org.springframework.security:spring-security-test")

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    runtimeOnly("com.h2database:h2")

    implementation("io.jsonwebtoken:jjwt:0.9.1")
    implementation("io.github.microutils:kotlin-logging:1.12.0")

    implementation("io.springfox:springfox-boot-starter:3.0.0")
    implementation("io.springfox:springfox-swagger-ui:3.0.0")

    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-mail")
    implementation("com.github.ulisesbocchio:jasypt-spring-boot-starter:2.0.0")
}
