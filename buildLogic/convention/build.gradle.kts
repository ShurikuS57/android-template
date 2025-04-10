import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "ru.taptm.buildLogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.compose.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = libs.plugins.app.android.application.asProvider().get().pluginId
            implementationClass = "AppPlugin"
        }
        register("androidApplicationCompose") {
            id = libs.plugins.app.android.application.compose.get().pluginId
            implementationClass = "AppComposePlugin"
        }
        register("androidLibraryCompose") {
            id = libs.plugins.app.android.library.compose.get().pluginId
            implementationClass = "LibraryComposePlugin"
        }
        register("androidLibrary") {
            id = libs.plugins.app.android.library.asProvider().get().pluginId
            implementationClass = "LibraryPlugin"
        }
        register("androidFeature") {
            id = libs.plugins.app.android.feature.get().pluginId
            implementationClass = "FeaturePlugin"
        }
        register("androidKoin") {
            id = libs.plugins.app.android.koin.get().pluginId
            implementationClass = "KoinPlugin"
        }
    }
}