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
        debug {
            buildConfigField("String", "BASE_URL", "\"https://reqres.in/api/\"")
        }
        release {
            buildConfigField("String", "BASE_URL", "\"https://reqres.in/api/\"")
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
        buildConfig = true
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

    //Okhttp
    implementation(libs.logging.interceptor)

    //DataStore
    implementation(libs.androidx.datastore.preferences)

    //JUnit4
    // Required -- JUnit 4 framework
    testImplementation ("junit:junit:4.13.2")
    // Optional -- Mockito framework (Mocking)
    testImplementation ("org.mockito:mockito-core:4.0.0")
    // Optional -- Mockito Kotlin (Mocking in Kotlin)
    testImplementation ("org.mockito.kotlin:mockito-kotlin:4.0.0")
    // Optional -- Mockk framework (Alternative mocking framework for Kotlin)
    testImplementation ("io.mockk:mockk:1.13.10")
    // Optional -- Robolectric (only if needed for Android-related tests)
    testImplementation ("androidx.test:core:1.6.1")



    testImplementation("app.cash.turbine:turbine:1.1.0")

    testImplementation("io.mockk:mockk:1.13.10") // or latest version

    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.9.0") // Ensure correct version
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.9.0")
    testImplementation ("org.junit.jupiter:junit-jupiter-api:5.8.2")// JUnit for tests



}