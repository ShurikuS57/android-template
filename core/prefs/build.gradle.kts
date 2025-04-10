plugins {
    alias(libs.plugins.app.android.library)
    alias(libs.plugins.app.android.koin)
    alias(libs.plugins.serialization)
    alias(libs.plugins.parcelize)
}

android {
    namespace = "io.taptm.prefs"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.security.crypto)
}