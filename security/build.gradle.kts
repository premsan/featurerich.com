plugins {
    `java-library`
    id("org.springframework.boot") version "3.3.3"
    id("io.spring.dependency-management") version "1.1.6"
    id("com.diffplug.spotless")
}

group = "com.featurerich"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":ui"))

    api("org.springframework.boot:spring-boot-starter-security")
    api("org.springframework.boot:spring-boot-starter-oauth2-client")
    api("org.springframework.session:spring-session-core")
    api("org.springframework.session:spring-session-jdbc")

    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.liquibase:liquibase-core")
    implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity6")
    implementation("org.asciidoctor:asciidoctorj:3.0.0")

    compileOnly("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("org.postgresql:postgresql")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}

spotless {
    format("html") {
        target("src/**/templates/**/*.html")
        prettier().config(mapOf("tabWidth" to 4))
    }
    java {
        googleJavaFormat("1.19.2").aosp().reflowLongStrings().skipJavadocFormatting()
        formatAnnotations()
    }
}