import org.shipkit.changelog.GenerateChangelogTask
import org.shipkit.github.release.GithubReleaseTask

buildscript {
    repositories {
        mavenCentral()
        google()
        maven { setUrl("https://plugins.gradle.org/m2/") }
    }

    dependencies {
        classpath("com.android.tools.build:gradle:7.4.0")
        classpath("com.vanniktech:gradle-maven-publish-plugin:0.22.0")
    }
}

plugins {
    id("com.github.ben-manes.versions") version "0.42.0"
    id("org.shipkit.shipkit-changelog") version "1.2.0"
    id("org.shipkit.shipkit-github-release") version "1.2.0"
    id("org.shipkit.shipkit-auto-version") version "1.2.2"
}

tasks {
    withType(GenerateChangelogTask::class) {
        previousRevision = project.ext["shipkit-auto-version.previous-tag"] as? String
        githubToken = System.getenv("GH_READ_TOKEN")
        repository = "DroidsOnRoids/Android-Debug-Database"
    }
    withType(GithubReleaseTask::class) {
        repository = "DroidsOnRoids/Android-Debug-Database"
        changelog = File(buildDir, "changelog.md")
        githubToken = System.getenv("GH_WRITE_TOKEN")
        newTagRevision = System.getenv("BITRISE_GIT_COMMIT")
    }
}

allprojects {
    repositories {
        mavenCentral()
        google()
    }
}

tasks.register("clean", Delete::class.java) {
    delete(rootProject.buildDir)
}