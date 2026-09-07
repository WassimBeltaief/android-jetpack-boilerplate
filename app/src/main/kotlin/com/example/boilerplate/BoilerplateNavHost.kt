package com.example.boilerplate

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.boilerplate.core.navigation.DetailRoute as DetailNavRoute
import com.example.boilerplate.core.navigation.HomeRoute as HomeNavRoute
import com.example.boilerplate.feature.detail.DetailRoute
import com.example.boilerplate.feature.home.HomeRoute

@Composable
fun BoilerplateNavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = HomeNavRoute,
        modifier = modifier,
    ) {
        composable<HomeNavRoute> {
            HomeRoute(
                onItemClick = { id -> navController.navigate(DetailNavRoute(id)) },
            )
        }
        composable<DetailNavRoute> {
            DetailRoute(
                onBackClick = { navController.popBackStack() },
            )
        }
    }
}
