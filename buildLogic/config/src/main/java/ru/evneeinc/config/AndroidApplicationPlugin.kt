package ru.evneeinc.config

import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import ru.evneeinc.config.extensions.libs

class AndroidApplicationPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            pluginManager.apply {
                apply("com.android.application")
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
                apply("kotlin-parcelize")
                apply("kotlinx-serialization")
            }
            extensions.configure<ApplicationExtension> {
                defaultConfig.targetSdk =
                    Integer.parseInt(libs.findVersion("projectTargetSdkVersion").get().toString())
                configureKotlin(this)
            }
            dependencies {
                add("implementation", "kotlinx.serialization")
            }
        }
    }
}