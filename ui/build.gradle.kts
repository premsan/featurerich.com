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
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
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