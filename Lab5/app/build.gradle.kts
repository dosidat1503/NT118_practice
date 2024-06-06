plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.lab5"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.lab5"
        minSdk = 31
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.paging.rxjava3)
    implementation(libs.androidx.uwb.rxjava3)
    implementation(libs.androidx.runtime.rxjava3)
    implementation(libs.androidx.room.paging.rxjava3)
    implementation(libs.androidx.room.rxjava3)
    implementation(libs.androidx.window.rxjava3)
    implementation(libs.androidx.work.rxjava3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}