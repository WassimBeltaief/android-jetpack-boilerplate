plugins {
    id("boilerplate.android.library")
    id("boilerplate.android.hilt")
    id("boilerplate.android.room")
}

dependencies {
    implementation(project(":core:model"))
}
