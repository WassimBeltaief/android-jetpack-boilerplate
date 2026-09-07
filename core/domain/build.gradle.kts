plugins {
    id("boilerplate.kotlin.jvm")
}

dependencies {
    implementation(project(":core:model"))
    implementation(libs.kotlinx.coroutines.android)
    implementation("javax.inject:javax.inject:1")
}
