
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies
import ru.taptm.buildLogic.configureKotlinJvmTarget
import ru.taptm.buildLogic.utils.implementation
import ru.taptm.buildLogic.utils.library

class FeaturePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(plugin = "app.android.library")
            }

            configureKotlinJvmTarget()

            dependencies {
                implementation(project(":designSystem"))

                implementation(library("androidx.lifecycle.runtimeCompose"))
                implementation(library("lifecycle.viewmodel.compose"))
                implementation(library("compose.navigation"))
                implementation(library("kotlinx.serialization.json"))
            }
        }
    }
}