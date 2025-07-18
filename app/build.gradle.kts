plugins {
    alias(libs.plugins.android.application)
}

android {
    buildFeatures {
        buildConfig = true
    }

    namespace = "com.dotgears.flappybird"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.dotgears.flappybird"
        minSdk = 21
//        targetSdk = 15
        targetSdk = 35
        versionCode = 4
        versionName = "1.3.1"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
    }

    lint {
        baseline = file("lint-baseline.xml")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    // oldest version on the google maven repo
    // unfortunately not old enough
//    implementation("com.google.android.gms:play-services-base:6.5.87")
//    implementation("com.google.android.gms:play-services-games:6.5.87")
//    implementation("com.google.android.gms:play-services-plus:6.5.87")
//    implementation("com.google.android.gms:play-services-appstate:6.5.87")

//    implementation(files("./lib/google-play-services.jar"))
    implementation("com.google.android.gms:play-services-games-v2:21.0.0")
    implementation(files("./lib/android-support-v4.jar"))

    // optional replacement for android-support-v4.jar
//    implementation(libs.activity)

    implementation(projects.andEngine)
    implementation(libs.splashscreen)
}