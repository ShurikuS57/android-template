
import com.google.devtools.ksp.gradle.KspExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import ru.taptm.buildLogic.utils.implementation
import ru.taptm.buildLogic.utils.ksp
import ru.taptm.buildLogic.utils.library

class KoinPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.google.devtools.ksp")
            }

            extensions.configure<KspExtension> {
                arg("KOIN_CONFIG_CHECK", "true")
                arg("KOIN_USE_COMPOSE_VIEWMODEL","true")
            }

            dependencies {
                implementation(platform(library("koin.bom")))
                implementation(library("koin.core"))
                implementation(library("koin.android"))
                implementation(library("koin.compose"))
                implementation(library("koin.startup"))
                implementation(library("koin.annotations"))
                ksp(library("koin.ksp.compiler"))
            }

        }

    }

}