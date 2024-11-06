plugins {
    id("java")
    id("com.diffplug.spotless") version "6.25.0"
}

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