package ru.taptm.buildLogic

import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile
import ru.taptm.buildLogic.utils.versionInt

internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        compileSdk = versionInt("targetSdk")

        defaultConfig {
            minSdk = versionInt("minSdk")
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
        }

    }
}

internal fun Project.configureKotlinJvmTarget() {
    configure<LibraryExtension> {
        tasks.withType<KotlinJvmCompile>().configureEach {
            compilerOptions {
                jvmTarget.set(JvmTarget.JVM_11)
                if (System.getProperty("idea.active") == "true") {
                    freeCompilerArgs.add("-Xdebug")
                }
            }
        }
    }
}