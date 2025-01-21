plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.serialization)
    alias(libs.plugins.parcelize)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "io.taptm.tops"
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
    implementation(projects.core.network)
    implementation(projects.core.common)
    implementation(projects.core.navigation)
    implementation(projects.core.prefs)
    implementation(projects.libs.themeSwitcher)
    implementation(projects.designSystem)
    implementation(projects.core.logger)

    implementation(libs.androidx.core.ktx)
    implementation(libs.kotlinx.serialization.json)

    // Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.composeUiBundle)
    implementation(libs.androidx.activity.compose)
    debugImplementation(libs.androidx.ui.tooling)

    // Coil
    implementation(libs.coil)

    // Koin
    implementation(platform(libs.koin.bom))
    implementation(libs.bundles.koinBundle)

    // Network
    implementation(libs.ktor.client)
}