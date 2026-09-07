plugins {
    id("boilerplate.android.library.compose")
}

dependencies {
    implementation(project(":core:designsystem"))
    implementation(project(":core:model"))
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.foundation)
}
