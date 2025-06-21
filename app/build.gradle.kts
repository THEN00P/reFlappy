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
        versionName = "1.3"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    implementation(files("./lib/GoogleAdMobAdsSdk-6.4.1.jar"))
    implementation(files("./lib/google-play-services.jar"))

    implementation(projects.andEngine)

    // oldest version on the google maven repo
    // unfortunately not old enough
//    implementation("com.google.android.gms:play-services-base:6.5.87")
//    implementation("com.google.android.gms:play-services-games:6.5.87")
//    implementation("com.google.android.gms:play-services-plus:6.5.87")
//    implementation("com.google.android.gms:play-services-appstate:6.5.87")



    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
//    testImplementation(libs.junit)
//    androidTestImplementation(libs.ext.junit)
//    androidTestImplementation(libs.espresso.core)
}