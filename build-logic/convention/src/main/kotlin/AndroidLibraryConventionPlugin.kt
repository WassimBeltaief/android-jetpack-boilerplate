import com.android.build.gradle.LibraryExtension
import com.example.boilerplate.buildlogic.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }
            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = 36

                // Auto-derive namespace from module path: :core:common → com.example.boilerplate.core.common
                namespace = "com.example.boilerplate" +
                    path
                        .split(":")
                        .drop(1)
                        .filter { it.isNotEmpty() }
                        .joinToString(".") { it }
                        .let { if (it.isNotEmpty()) ".$it" else "" }

                resourcePrefix = path
                    .split("""\W""".toRegex())
                    .drop(1)
                    .distinct()
                    .joinToString(separator = "_")
                    .lowercase() + "_"
            }
        }
    }
}
