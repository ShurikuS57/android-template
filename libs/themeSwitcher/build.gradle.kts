plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.parcelize)
}

android {
    namespace = "io.taptm.themeSwicher"
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
    implementation(projects.core.prefs)
    implementation(libs.androidx.core.ktx)
    implementation(libs.lifecycle.viewmodel.compose)

    // Koin
    implementation(platform(libs.koin.bom))
    implementation(libs.bundles.koinBundle)
}