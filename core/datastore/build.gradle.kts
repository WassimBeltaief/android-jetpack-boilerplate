plugins {
    id("boilerplate.android.library")
    id("boilerplate.android.hilt")
}

dependencies {
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.kotlinx.coroutines.android)
}
