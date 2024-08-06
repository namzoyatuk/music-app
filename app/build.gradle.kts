plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.music_app"
    compileSdk = 34
    viewBinding.isEnabled = true

    defaultConfig {
        applicationId = "com.example.music_app"
        minSdk = 24
        targetSdk = 34
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
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.truth)
    implementation(libs.media3.common)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.rules)
    implementation(libs.media3.exoplayer)
    implementation(libs.exoplayer.ui)

    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)
    implementation(libs.browser)
    implementation(libs.spotify.auth)
    annotationProcessor(libs.lombok)
    implementation(libs.lombok)
    implementation(libs.room)
    annotationProcessor(libs.room.compiler)
    implementation(libs.bumptech)
    androidTestImplementation(libs.mockito)
}