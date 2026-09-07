plugins {
    id("boilerplate.android.library")
}

dependencies {
    api(project(":core:domain"))
    api(project(":core:model"))

    // Expose test utilities as api so consuming modules can use them directly
    api(libs.junit4)
    api(libs.kotlinx.coroutines.test)
    api(libs.turbine)
    api(libs.mockk)
    api(libs.roborazzi)
    api(libs.roborazzi.compose)
    api(libs.roborazzi.rule)
    api(libs.robolectric)
}
