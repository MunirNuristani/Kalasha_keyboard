plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.kalasha.keyboard.ala"
    compileSdk = 35

    signingConfigs {
        create("release") {
            keyAlias = "kalasha-keyboard"
            keyPassword = "kalasha2024"
            storeFile = file("../kalasha-release-key.keystore")
            storePassword = "kalasha2024"
        }
    }

    defaultConfig {
        applicationId = "com.kalasha.keyboard.ala"
        minSdk = 26
        targetSdk = 35
        versionCode = 5
        versionName = "1.2.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
    }
    
    bundle {
        language {
            enableSplit = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    // Minimal dependencies for keyboard service only
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}