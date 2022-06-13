import org.jetbrains.compose.compose
import java.util.Properties

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose").version(Versions.compose_jb)
    id("com.android.library")
}

kotlin {
    android()
    jvm()
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.foundation)
                // @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.material)
                implementation(projects.imageLoader)
            }
        }
        val androidMain by getting
        val jvmMain by sourceSets.getting
    }
}

android {
    compileSdk = Versions.Android.compile
    buildToolsVersion = Versions.Android.buildTools
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = Versions.Android.min
        targetSdk = Versions.Android.target
    }
    compileOptions {
        sourceCompatibility = Versions.Java.java
        targetCompatibility = Versions.Java.java
    }
}
