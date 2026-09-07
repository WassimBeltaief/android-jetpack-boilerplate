import com.android.build.api.dsl.ApplicationExtension
import com.example.boilerplate.buildlogic.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("org.jetbrains.kotlin.plugin.compose")
            }
            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = 36
                buildFeatures.buildConfig = true
                productFlavors {
                    flavorDimensions += "environment"
                    create("dev") {
                        dimension = "environment"
                        applicationIdSuffix = ".dev"
                        versionNameSuffix = "-dev"
                        buildConfigField("String", "BASE_URL", "\"https://api.dev.example.com/\"")
                    }
                    create("prod") {
                        dimension = "environment"
                        buildConfigField("String", "BASE_URL", "\"https://api.example.com/\"")
                    }
                }
            }
        }
    }
}
