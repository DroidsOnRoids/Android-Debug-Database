import com.vanniktech.maven.publish.MavenPublishBaseExtension
import com.vanniktech.maven.publish.SonatypeHost

plugins {
    id("maven-publish")
    id("com.android.library")
    id("com.vanniktech.maven.publish")
}

group = "pl.droidsonroids.androiddebugdatabase"

android {
    compileSdkVersion(33)

    defaultConfig {
        minSdk = 14
        targetSdk = 33
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        resValue("string", "PORT_NUMBER", "8080")
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    namespace = "com.amitshekhar.debug"

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

extensions.findByType(MavenPublishBaseExtension::class)?.apply {
    publishToMavenCentral(
        host = SonatypeHost.DEFAULT,
        automaticRelease = true // instead of closeAndReleaseRepository
    )
    signAllPublications()
}

dependencies {
    implementation("com.google.code.gson:gson:2.9.1")
    implementation("androidx.room:room-runtime:2.5.0")
}