plugins {
    alias(libs.plugins.app.android.library)
    alias(libs.plugins.app.android.koin)
    alias(libs.plugins.serialization)
}

android {
    namespace = "io.taptm.network"
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.logger)
    implementation(projects.libs.mock)
    implementation(libs.androidx.core.ktx)

    // Network
    implementation(platform(libs.ktor.bom))
    implementation(libs.bundles.ktorBundle)

    // Pluto
    debugImplementation(libs.bundles.plutoDebugBundle)
    releaseImplementation(libs.bundles.plutoProdBundle)

    // Flocon
    debugImplementation(libs.flocon.ktor)
}