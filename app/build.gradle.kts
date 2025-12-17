plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    // Добавляем этот плагин для работы с View Binding/Data Binding
    id("kotlin-parcelize")
}

android {
    namespace = "com.example.sportsterlitamak"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.example.sportsterlitamak"
        minSdk = 24
        targetSdk = 36
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    // Включаем View Binding для работы адаптера
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // Добавленные библиотеки для вашего приложения
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    implementation("androidx.cardview:cardview:1.0.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")

    // Яндекс Карты
    implementation("com.yandex.android:maps.mobile:4.5.1-lite")

    // Для работы с местоположением
    implementation("com.google.android.gms:play-services-location:21.2.0")

    // Для работы с интернетом
    implementation("com.squareup.okhttp3:okhttp:4.12.0")

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}