plugins {
    alias(libs.plugins.app.android.library.compose)
}

android {
    namespace = "io.taptm.designsystem"
}

dependencies {
    api(projects.core.common)
    implementation(libs.androidx.core.ktx)

    // Coil
    api(libs.coil)

}