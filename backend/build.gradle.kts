import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.3.5.RELEASE" apply false
	id("io.spring.dependency-management") version "1.0.10.RELEASE" apply false
	kotlin("jvm") version "1.3.72" apply false
	kotlin("plugin.spring") version "1.3.72" apply false
	kotlin("plugin.jpa") version "1.3.72" apply false
}

allprojects {
	group = "com.adikosa.todolistk"
	version = "0.0.1-SNAPSHOT"

	tasks.withType<JavaCompile> {
		sourceCompatibility = "1.8"
		targetCompatibility = "1.8"
	}

	tasks.withType<KotlinCompile> {
		kotlinOptions {
			freeCompilerArgs = listOf("-Xjsr305=strict")
			jvmTarget = "1.8"
		}
	}
}

subprojects {
	repositories {
		mavenCentral()
	}

	apply {
		plugin("io.spring.dependency-management")
	}
}
