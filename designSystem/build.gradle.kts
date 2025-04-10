plugins {
    alias(libs.plugins.app.android.library.compose)
}

android {
    namespace = "io.taptm.designsystem"
}

dependencies {
    implementation(projects.core.common)
    implementation(libs.androidx.core.ktx)

    // Coil
    implementation(libs.coil)

}