plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("org.jetbrains.kotlin.plugin.serialization") version "2.0.20"
    alias(libs.plugins.safeargs)
    id("kotlin-parcelize")
    //Hilt
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
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.15"
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        compose = true
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
    
    implementation (libs.okhttp)
    implementation (libs.retrofit)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //Hilt
    implementation("com.google.dagger:hilt-android:2.51.1")
    kapt("com.google.dagger:hilt-android-compiler:2.51.1")

    //Compose
    implementation(libs.androidx.activity.compose)

    implementation(libs.androidx.ui)

    implementation(libs.androidx.material3)

    implementation(libs.androidx.ui.tooling.preview)

    debugImplementation(libs.androidx.ui.tooling)

    implementation ("androidx.compose.material:material-icons-extended:1.7.8")



    implementation ("androidx.compose.ui:ui:1.4.0")
    implementation ("androidx.compose.ui:ui-tooling-preview:1.4.0")
    implementation ("androidx.compose.material:material:1.7.8")

}