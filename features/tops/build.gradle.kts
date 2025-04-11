plugins {
    alias(libs.plugins.app.android.feature)
    alias(libs.plugins.app.android.library.compose)
    alias(libs.plugins.app.android.koin)
}

android {
    namespace = "io.taptm.tops"
}

dependencies {
    implementation(projects.core.network)
    implementation(projects.core.common)
    implementation(projects.core.navigation)
    implementation(projects.core.prefs)
    implementation(projects.libs.themeSwitcher)
    implementation(projects.core.logger)

    // Network
    implementation(libs.ktor.client)
}