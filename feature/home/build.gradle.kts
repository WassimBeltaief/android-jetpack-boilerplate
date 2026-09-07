plugins {
    id("boilerplate.android.feature")
    alias(libs.plugins.roborazzi)
}

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":core:model"))
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.foundation)
}
