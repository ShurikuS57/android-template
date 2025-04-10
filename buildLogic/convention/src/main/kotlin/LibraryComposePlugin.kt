
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import ru.taptm.buildLogic.configureAndroidCompose
import ru.taptm.buildLogic.configureKotlinAndroid
import ru.taptm.buildLogic.configureKotlinJvmTarget


class LibraryComposePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.plugin.compose")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                defaultConfig.testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                testOptions.animationsDisabled = true
            }

            configureKotlinJvmTarget()

            val extension = extensions.getByType<LibraryExtension>()
            configureAndroidCompose(extension)
        }
    }

}

//plugins {
//    alias(libs.plugins.app.android.library)
//    alias(libs.plugins.compose.compiler)
//}
//
//android {
//    namespace = "io.taptm.designsystem"
//}
//
//dependencies {
//    implementation(projects.core.common)
//    implementation(libs.androidx.core.ktx)
//
//    // Compose
//    implementation(platform(libs.androidx.compose.bom))
//    implementation(libs.bundles.composeUiBundle)
//    implementation(libs.androidx.activity.compose)
//    debugImplementation(libs.androidx.ui.tooling)
//}