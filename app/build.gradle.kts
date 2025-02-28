plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("org.jetbrains.kotlin.plugin.serialization") version "2.0.20"
    alias(libs.plugins.safeargs)
    id("kotlin-parcelize")
    alias(libs.plugins.google.android.libraries.mapsplatform.secrets.gradle.plugin)


    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")


}

android {
    namespace = "com.example.homeworktbc"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.homeworktbc"
        minSdk = 24
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
val paging_version = "3.3.5"
dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.kotlinx.serialization.json.v160)
    implementation (libs.retrofit2.kotlinx.serialization.converter)

    implementation(libs.androidx.paging.runtime)

    //Map
    implementation(libs.android.maps.utils)
    implementation(libs.maps.utils.ktx)
    //git
    implementation (libs.play.services.maps.v1910)

    // Lifecycle Runtime KTX Library
    implementation (libs.androidx.lifecycle.runtime.ktx)

    // Maps SDK for Android KTX Library
    implementation (libs.android.maps.ktx)
    ///////
    implementation (libs.play.services.location)



    //Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    implementation (libs.okhttp)
    implementation (libs.retrofit)
    implementation(libs.play.services.maps)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}