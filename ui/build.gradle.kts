plugins {
    id("java")
    id("com.diffplug.spotless")
}

group = "com.featurerich"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform(libs.junit.bom))
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