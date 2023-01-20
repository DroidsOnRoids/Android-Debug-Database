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
    api(project(":debug-db-base"))
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}