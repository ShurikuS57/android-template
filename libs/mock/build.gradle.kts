plugins {
    alias(libs.plugins.app.android.library)
    alias(libs.plugins.serialization)
    alias(libs.plugins.parcelize)
}

android {
    namespace = "io.taptm.mock"
}

dependencies {
    implementation(libs.androidx.core.ktx)

    implementation(libs.kotlinx.serialization.json)

    // Network
    implementation(platform(libs.ktor.bom))
    implementation(libs.bundles.ktorBundle)
}