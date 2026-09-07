package com.example.boilerplate.feature.home

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onRoot
import com.example.boilerplate.core.designsystem.theme.AppTheme
import com.example.boilerplate.core.testing.data.testItems
import com.github.takahirom.roborazzi.captureRoboImage
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34])
class HomeScreenScreenshotTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun homeScreen_loading() {
        composeTestRule.setContent {
            AppTheme(dynamicColor = false) {
                HomeScreen(uiState = HomeUiState.Loading, onItemClick = {})
            }
        }
        composeTestRule.onRoot().captureRoboImage()
    }

    @Test
    fun homeScreen_success() {
        composeTestRule.setContent {
            AppTheme(dynamicColor = false) {
                HomeScreen(uiState = HomeUiState.Success(testItems), onItemClick = {})
            }
        }
        composeTestRule.onRoot().captureRoboImage()
    }

    @Test
    fun homeScreen_error() {
        composeTestRule.setContent {
            AppTheme(dynamicColor = false) {
                HomeScreen(uiState = HomeUiState.Error("Something went wrong"), onItemClick = {})
            }
        }
        composeTestRule.onRoot().captureRoboImage()
    }
}
