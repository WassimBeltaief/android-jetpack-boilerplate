plugins {
    id("boilerplate.android.library")
    id("boilerplate.android.hilt")
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:model"))
    implementation(project(":core:network"))
    implementation(project(":core:database"))
    implementation(project(":core:domain"))
    implementation(libs.kotlinx.coroutines.android)

    testImplementation(project(":core:testing"))
}
