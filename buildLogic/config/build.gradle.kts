plugins {
    `kotlin-dsl`
}
group = "ru.evneeinc.funProject.buildLogic"

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin{
    plugins {
        register("AndroidApplicationPlugin") {
            id = "ru.evneeinc.funProject.AndroidApplicationPlugin"
            implementationClass = "AndroidApplicationPlugin"
        }
    }
}