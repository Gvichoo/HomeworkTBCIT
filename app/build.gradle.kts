plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("org.jetbrains.kotlin.plugin.serialization") version "2.0.20"
    alias(libs.plugins.safeargs)
    id("kotlin-parcelize")
    alias(libs.plugins.google.gms.google.services)
    //Hilt
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    //Room
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.homeworktbc"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.homeworktbc"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}
dependencies {
    val room_version = "2.6.1"
    val paging_version = "3.3.5"
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.kotlinx.serialization.json.v160)
    implementation (libs.retrofit2.kotlinx.serialization.converter)

    implementation(libs.androidx.paging.runtime)

    //Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    //Glide
    implementation (libs.glide)
    //DataStore Preferences
    implementation(libs.androidx.datastore.preferences)
    //Splash
    implementation(libs.androidx.core.splashscreen)
    //Room
    implementation("androidx.room:room-runtime:$room_version")
    ksp("androidx.room:room-compiler:$room_version")
    implementation("androidx.room:room-ktx:$room_version")

    implementation (libs.okhttp)
    implementation (libs.retrofit)
    implementation(libs.firebase.auth)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}