plugins {
    id("java")
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
    requiresUnpack("**/asciidoctorj-*.jar")
}

dependencies {
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    implementation(project(":security"))
    implementation(project(":blog"))
    implementation(project(":ui"))
    implementation(project(":grep"))
    implementation(project(":json"))
    implementation(project(":mock"))

    implementation("org.springframework.boot:spring-boot-starter")

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}