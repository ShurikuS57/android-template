plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.serialization)
    alias(libs.plugins.parcelize)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "io.taptm.network"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = libs.versions.kotlinJvmTarget.get()
    }
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.libs.mock)
    implementation(libs.androidx.core.ktx)
    implementation(libs.kotlinx.serialization.json)



    // Network
    implementation(platform(libs.ktor.bom))
    implementation(libs.bundles.ktorBundle)

    // Dagger/hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    // Pluto
    debugImplementation(libs.bundles.plutoDebugBundle)
    releaseImplementation(libs.bundles.plutoProdBundle)
}