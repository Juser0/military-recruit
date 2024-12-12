import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.3.5"
	id("io.spring.dependency-management") version "1.1.6"
}

group = "com.military"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	// Spring Web
	implementation("org.springframework.boot:spring-boot-starter-web")

	// Thymeleaf
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")

	// Spring Cache
	implementation("org.springframework.boot:spring-boot-starter-cache")
	implementation ("com.github.ben-manes.caffeine:caffeine")

//	// Spring Data JDBC
//	implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
//
//	// PostgreSQL
//	runtimeOnly("org.postgresql:postgresql")

	// XML
	implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml")

	// Common Dependencies
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")

	// Test Dependencies
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<BootJar> {
	layered
}

tasks.withType<Test> {
	useJUnitPlatform()
}
