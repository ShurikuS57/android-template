plugins {
    alias(libs.plugins.app.android.library)
    alias(libs.plugins.serialization)
    alias(libs.plugins.parcelize)
}

android {
    namespace = "io.taptm.logger"
}

dependencies {
    implementation(projects.core.common)
    implementation(libs.androidx.core.ktx)

    // Pluto
    debugImplementation(libs.bundles.plutoDebugBundle)
    releaseImplementation(libs.bundles.plutoProdBundle)
}