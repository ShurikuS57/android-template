package ru.taptm.buildLogic.utils

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.support.delegates.DependencyHandlerDelegate

internal val Project.libs
    get(): VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

internal fun Project.library(libraryName: String) = libs.findLibrary(libraryName).get()

internal fun Project.version(key: String): String = extensions
    .getByType<VersionCatalogsExtension>()
    .named("libs")
    .findVersion(key)
    .get()
    .requiredVersion

internal fun Project.versionInt(key:String) = version(key).toInt()

internal fun DependencyHandlerDelegate.implementation(dependency: Any) {
    add("implementation", dependency)
}

internal fun DependencyHandlerDelegate.testImplementation(dependency: Any) {
    add("testImplementation", dependency)
}

internal fun DependencyHandlerDelegate.androidTestImplementation(dependency: Any) {
    add("androidTestImplementation", dependency)
}

internal fun DependencyHandlerDelegate.ksp(dependency: Any) {
    add("ksp", dependency)
}