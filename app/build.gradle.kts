
import io.gitlab.arturbosch.detekt.DetektCreateBaselineTask
import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.app.android.application)
    alias(libs.plugins.app.android.application.compose)
    alias(libs.plugins.app.android.koin)
    alias(libs.plugins.serialization)
    alias(libs.plugins.easylauncher)
    alias(libs.plugins.detekt)
}

val versionMajor = libs.versions.versionMajor.get().toInt()
val versionMinor = libs.versions.versionMinor.get().toInt()
val versionPatch = libs.versions.versionPatch.get().toInt()

val versionCodeValue = versionMajor * 10000 + versionMinor * 100 + versionPatch
val versionNameValue = "${versionMajor}.${versionMinor}.${versionPatch}"

fun getVersionCode(): Int {
    val taskName = gradle.startParameter.taskNames.toString()
    println("task name: $taskName")
    return if (taskName.contains("ProdRelease")) {
        versionCodeValue
    } else {
        System.getenv("CI_PIPELINE_IID")?.toString()?.toIntOrNull() ?: versionCodeValue
    }
}

val devUrl = "https://api.jikan.moe/"

android {
    namespace = "io.taptm.sample"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "io.taptm.sample"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = getVersionCode()
        versionName = versionNameValue

        setProperty("archivesBaseName", "app$versionName($versionCode)")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

    }

    signingConfigs {
        getByName("debug") {
            rootProject.file("keystore.properties").also { keystorePropertiesFile ->
                if (keystorePropertiesFile.canRead()) {
                    val keystoreProperties = Properties()
                    keystoreProperties.load(FileInputStream(keystorePropertiesFile))
                    keyAlias = keystoreProperties.getProperty("keyAlias")
                    keyPassword = keystoreProperties.getProperty("keyPassword")
                    storeFile = file(keystoreProperties.getProperty("storeFile"))
                    storePassword = keystoreProperties.getProperty("storePassword")
                } else {
                    println("Unable to read keystore.properties")
                }
            }
        }

        create("release") {
            rootProject.file("keystore.properties").also { keystorePropertiesFile ->
                if (keystorePropertiesFile.canRead()) {
                    val keystoreProperties = Properties()
                    keystoreProperties.load(FileInputStream(keystorePropertiesFile))
                    keyAlias = keystoreProperties.getProperty("keyAlias")
                    keyPassword = keystoreProperties.getProperty("keyPassword")
                    storeFile = file(keystoreProperties.getProperty("storeFile"))
                    storePassword = keystoreProperties.getProperty("storePassword")
                } else {
                    println("Unable to read keystore.properties")
                }
            }
        }
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            isDebuggable = true
            versionNameSuffix = "-debug"
            applicationIdSuffix = ".debug"
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }

        release {
            isMinifyEnabled = true
            isDebuggable = false
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    flavorDimensions += "version"
    productFlavors {
        create("dev") {
            dimension = "version"
            buildConfigField("String", "URL", "\"${devUrl}\"")
        }
        create("prod") {
            dimension = "version"
            buildConfigField("String", "URL", "\"${devUrl}\"")
        }
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    kotlinOptions {
        jvmTarget = libs.versions.kotlinJvmTarget.get()
    }

    kotlin {
        compilerOptions {
            if (System.getProperty("idea.active") == "true") {
                println("Enable coroutine debugging")
                freeCompilerArgs.add("-Xdebug")
            }
        }
    }
    lint {
        lintConfig = file("${rootDir}/lint/lint.xml")
        baseline = file("${rootDir}/lint/lint-baseline.xml")
        checkDependencies = true
        abortOnError = true
        warningsAsErrors = true
    }

    detekt {
        source.setFrom("src/main/java", "src/main/kotlin")
        config.setFrom("${rootDir}/lint/detekt.yml")
        baseline = file("${rootDir}/lint/detekt-baseline.xml")
    }
}

dependencies {
    // Modules
    implementation(projects.designSystem)
    implementation(projects.core.common)
    implementation(projects.core.navigation)
    implementation(projects.core.network)
    implementation(projects.core.prefs)
    implementation(projects.core.logger)
    implementation(projects.features.tops)
    implementation(projects.libs.themeSwitcher)

    // AndroidX
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.splash.screen)

    // Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.composeUiBundle)
    implementation(libs.androidx.activity.compose)
    debugImplementation(libs.androidx.ui.tooling)

    // Coil
    implementation(libs.coil)

    // Pluto
    debugImplementation(libs.bundles.plutoDebugBundle)
    releaseImplementation(libs.bundles.plutoProdBundle)

    // Flocon
    debugImplementation(libs.flocon.debug)
    releaseImplementation(libs.flocon.prod)

    // Test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

val detektProjectBaseline by tasks.registering(DetektCreateBaselineTask::class) {
    description = "Overrides current baseline."
    buildUponDefaultConfig.set(true)
    ignoreFailures.set(true)
    parallel.set(true)
    setSource(files(rootDir))
    config.setFrom(files("${rootDir}/lint/detekt.yml"))
    baseline.set(file("${rootDir}/lint/detekt-baseline.xml"))
    include("**/*.kt")
    include("**/*.kts")
    exclude("**/resources/**")
    exclude("**/build/**")
}

easylauncher {
    productFlavors {
        register("dev") {
            enable(true)
            defaultFlavorNaming(true)
            filters(chromeLike(label = "$versionNameValue(${getVersionCode()})"))
            setIconNames(
                listOf(
                    "@mipmap/ic_launcher",
                    "@mipmap/ic_launcher_round",
                )
            )
        }
    }
}