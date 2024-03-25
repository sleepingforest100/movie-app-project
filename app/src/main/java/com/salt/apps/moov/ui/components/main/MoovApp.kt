package com.salt.apps.moov.ui.components.main

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.salt.apps.moov.ui.navigation.MoovNavHost
import com.salt.apps.moov.ui.navigation.MoovScreen.DETAIL
import com.salt.apps.moov.ui.navigation.MoovScreen.FAVORITE
import com.salt.apps.moov.ui.navigation.MoovScreen.HOME

@Composable
fun MoovApp(
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = backStackEntry?.destination?.route?.let { route ->
        when (route) {
            HOME.route -> HOME
            "${DETAIL.route}/{movieId}" -> DETAIL
            FAVORITE.route -> FAVORITE
            else -> null
        }
    } ?: HOME

    Scaffold(
        topBar = {
            MoovTopAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = {
                    navController.navigateUp()
                },
                onFavoriteClicked = {
                    navController.navigate(FAVORITE.route)
                }
            )
        },
        content = { innerPadding ->
            MoovNavHost(navController = navController, innerPadding = innerPadding)
        }
    )
}