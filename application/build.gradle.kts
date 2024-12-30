plugins {
    `java-library`
    alias(libs.plugins.org.springframework.boot)
    alias(libs.plugins.io.spring.dependency.management)
    alias(libs.plugins.com.diffplug.spotless)
}

group = "com.featurerich"

val artifactVersion: String by rootProject.extra
version = artifactVersion

repositories {
    mavenCentral()
}

tasks.bootJar {
    enabled = false
}

dependencies {
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    implementation(project(":security"))
    implementation(project(":ui"))
    implementation("org.springframework.boot:spring-boot-starter-web")

    testImplementation(platform(libs.org.junit.junit.bom))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

spotless {
    format("html") {
        val htmlTabWidth: Int by rootProject.extra
        prettier().config(mapOf("tabWidth" to htmlTabWidth))

        target("src/**/templates/**/*.html")
    }
    java {
        val googleJavaFormatVersion: String by rootProject.extra

        googleJavaFormat(googleJavaFormatVersion).aosp().reflowLongStrings().skipJavadocFormatting()
        formatAnnotations()
    }
}