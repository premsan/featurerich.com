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

    implementation(project(":blog"))
    implementation(project(":grep"))
    implementation(project(":json"))
    implementation(project(":mock"))
    implementation(project(":reservation"))
    implementation(project(":scheduled"))
    implementation(project(":security"))
    implementation(project(":ui"))

    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
    runtimeOnly("com.h2database:h2")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}