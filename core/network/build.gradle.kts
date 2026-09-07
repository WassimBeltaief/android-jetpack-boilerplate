plugins {
    id("boilerplate.android.library")
    id("boilerplate.android.hilt")
}

dependencies {
    api(project(":core:model"))
    api(project(":core:common"))

    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.okhttp.core)
    implementation(libs.okhttp.logging)
    implementation(libs.kotlinx.serialization.json)
}
