plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("org.jetbrains.kotlin.plugin.serialization") version "2.0.20"
    alias(libs.plugins.safeargs)
    id("kotlin-parcelize")
    id("com.google.devtools.ksp")
    //HILT
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
    kapt {
        correctErrorTypes = true
    }
}

dependencies {
    val paging_version = "3.3.5"
    val room_version = "2.6.1"
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.kotlinx.serialization.json.v160)
    implementation (libs.retrofit2.kotlinx.serialization.converter)
    implementation (libs.jetbrains.kotlinx.serialization.json.v160)
    implementation (libs.androidx.navigation.fragment.ktx)
    implementation (libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.kotlinx.coroutines.android)
    implementation (libs.androidx.lifecycle.livedata.ktx)

    //paging
    implementation(libs.androidx.paging.runtime)
    //moshi
    implementation (libs.moshi)
    implementation (libs.moshi.kotlin)
    implementation (libs.converter.moshi)
    //glide
    implementation (libs.glide)
    //dataStore
    implementation(libs.androidx.datastore.preferences)
    //proto
    implementation  (libs.androidx.datastore)
    implementation  (libs.protobuf.javalite)
    //room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.paging)
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
    //repository
    implementation (libs.androidx.paging.runtime)
    implementation (libs.androidx.room.ktx)
    //HILT
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)


    val hilt_version = "2.55" // Ensure this is the latest stable version

    implementation(libs.hilt.android.v250)
    kapt(libs.hilt.android.compiler.v250)



    implementation (libs.okhttp)
    implementation (libs.retrofit)
    implementation(libs.androidx.runner)
    implementation(libs.androidx.espresso.core)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

}




