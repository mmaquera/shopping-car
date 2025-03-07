plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("kotlin-kapt")
    id("org.jetbrains.kotlin.plugin.serialization") version "2.0.0"
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.mmaquera.odoo.shopping"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.mmaquera.odoo.shopping"
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
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_21.toString()
        freeCompilerArgs = freeCompilerArgs + "-Xlint:deprecation"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Viewmodel compose
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.5")

    // Constraint
    implementation("androidx.constraintlayout:constraintlayout-compose:1.1.0")

    // Ktor
    implementation("io.ktor:ktor-client-core:2.3.4") // Versión más reciente
    implementation("io.ktor:ktor-client-cio:2.3.4") // Motor de red
    implementation("io.ktor:ktor-client-content-negotiation:2.3.4") // Negociación de contenido
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.4") // Serialización JSON
    implementation("io.ktor:ktor-client-logging:2.3.4") // Logging
    implementation("ch.qos.logback:logback-classic:1.2.11")

    //dagger hilt
    implementation("com.google.dagger:hilt-android:2.48") // Dependencia principal de Hilt
    kapt("com.google.dagger:hilt-android-compiler:2.48") // Procesador de anotaciones de Hilt
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")

    //splash api
    implementation("androidx.core:core-splashscreen:1.0.0")

    //Navigation
    implementation("androidx.navigation:navigation-compose:2.8.8")

    implementation ("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")

    // EncryptSharedPreferences
    implementation ("androidx.security:security-crypto:1.1.0-alpha06")
}
