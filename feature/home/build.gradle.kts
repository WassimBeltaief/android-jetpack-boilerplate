plugins {
    id("boilerplate.android.feature")
    alias(libs.plugins.roborazzi)
}

roborazzi {
    outputDir.set(project.file("src/test/snapshots"))
}

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":core:model"))
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.foundation)
}
