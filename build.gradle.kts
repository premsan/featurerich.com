plugins {
    id("java")
    alias(libs.plugins.com.diffplug.spotless)
}

/*
 * Extra properties
 */
val googleJavaFormatVersion by extra("1.19.2")
val htmlTabWidth by extra(4)

group = "com.featurerich"
version = "0.0.1-SNAPSHOT"

spotless {
    ratchetFrom("origin/main")

    format("misc") {
        target("*.gradle", ".gitattributes", ".gitignore")

        trimTrailingWhitespace()
        indentWithTabs()
        endWithNewline()
    }
}