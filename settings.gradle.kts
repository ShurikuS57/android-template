enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Sample"
include(
    ":app",
    ":designSystem",
    ":core:navigation",
    ":core:network",
    ":core:prefs",
    ":core:common",
    ":core:logger",
    ":libs:mock",
    ":libs:themeSwitcher",
    ":features:tops",
)
include(":designsystemapp")
