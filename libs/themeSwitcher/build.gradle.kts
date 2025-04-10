plugins {
    alias(libs.plugins.app.android.library)
    alias(libs.plugins.app.android.koin)
}

android {
    namespace = "io.taptm.themeSwicher"
}

dependencies {
    implementation(projects.core.prefs)
    implementation(libs.androidx.core.ktx)
    implementation(libs.lifecycle.viewmodel.compose)
}