package com.example.boilerplate.feature.home

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.example.boilerplate.core.testing.data.testItems
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun showsLoadingIndicator_whenLoading() {
        composeTestRule.setContent {
            HomeScreen(
                uiState = HomeUiState.Loading,
                onItemClick = {},
            )
        }
        composeTestRule.onNodeWithContentDescription("Loading").assertIsDisplayed()
    }

    @Test
    fun showsItems_whenSuccess() {
        composeTestRule.setContent {
            HomeScreen(
                uiState = HomeUiState.Success(testItems),
                onItemClick = {},
            )
        }
        composeTestRule.onNodeWithText(testItems[0].title).assertIsDisplayed()
        composeTestRule.onNodeWithText(testItems[1].title).assertIsDisplayed()
    }

    @Test
    fun showsError_whenError() {
        composeTestRule.setContent {
            HomeScreen(
                uiState = HomeUiState.Error("Something went wrong"),
                onItemClick = {},
            )
        }
        composeTestRule.onNodeWithText("Something went wrong").assertIsDisplayed()
    }
}
