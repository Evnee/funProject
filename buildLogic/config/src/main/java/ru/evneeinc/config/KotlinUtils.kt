package ru.evneeinc.config

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.provideDelegate
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
import ru.evneeinc.config.extensions.libs

internal fun Project.configureKotlin(
    commonExtension: CommonExtension<*, *, *, *, *>,
) {
    commonExtension.apply {
        compileSdk = Integer.parseInt(libs.findVersion("projectCompileSdkVersion").get().toString())
        defaultConfig {
            minSdk = Integer.parseInt(libs.findVersion("projectMinSdkVersion").get().toString())
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }

        kotlinOptions {
            // Рассматривайте все предупреждения Kotlin как ошибки (по умолчанию отключено)
            // Переопределите, установив значение warningsAsErrors=true в ~/.gradle/gradle.properties
            val warningsAsErrors: String? by project
            allWarningsAsErrors = warningsAsErrors.toBoolean()

            // Установите для JVM целевое значение 17
            jvmTarget = JavaVersion.VERSION_1_8.toString()
        }

        packaging {
            resources {
                excludes += "/META-INF/{AL2.0,LGPL2.1}"
            }
        }
    }
}

private fun CommonExtension<*, *, *, *, *>.kotlinOptions(block: KotlinJvmOptions.() -> Unit) {
    (this as ExtensionAware).extensions.configure("kotlinOptions", block)
}
