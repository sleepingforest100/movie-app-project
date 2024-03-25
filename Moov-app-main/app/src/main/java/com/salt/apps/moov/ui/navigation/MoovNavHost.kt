package com.salt.apps.moov.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.salt.apps.moov.ui.navigation.MoovScreen.DETAIL
import com.salt.apps.moov.ui.navigation.MoovScreen.FAVORITE
import com.salt.apps.moov.ui.navigation.MoovScreen.HOME
import com.salt.apps.moov.ui.screens.detail.DetailScreen
import com.salt.apps.moov.ui.screens.favorite.FavoriteScreen
import com.salt.apps.moov.ui.screens.home.HomeScreen

@Composable
fun MoovNavHost(navController: NavHostController, innerPadding: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = HOME.route,
        modifier = Modifier.padding(innerPadding),
    ) {
        homeComposable(navController = navController)
        detailComposable(navController = navController)
        favoriteComposable(navController = navController)
    }
}

fun NavGraphBuilder.homeComposable(navController: NavHostController) {
    composable(
        route = HOME.route,
        enterTransition = {
            return@composable slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Start, tween(500)
            )
        },
        exitTransition = {
            return@composable slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Start, tween(500)
            )
        },
        popEnterTransition = {
            return@composable slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.End, tween(500)
            )
        },
        content = {
            HomeScreen(
                navController = navController,
            )
        }
    )
}

fun NavGraphBuilder.detailComposable(navController: NavHostController) {
    composable(
        route = "${DETAIL.route}/{movieId}",
        arguments = listOf(
            navArgument(name = "movieId") {
                type = NavType.IntType
            }
        ),
        enterTransition = {
            return@composable slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Start, tween(500)
            )
        },
        exitTransition = {
            return@composable slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.End, tween(500)
            )
        },
        popEnterTransition = {
            return@composable slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Start, tween(500)
            )
        },
        content = {
            val movieId = navController.currentBackStackEntry?.arguments?.getInt("movieId")
            DetailScreen(movieId = movieId)
        }
    )
}

fun NavGraphBuilder.favoriteComposable(navController: NavHostController) {
    composable(
        route = FAVORITE.route,
        enterTransition = {
            return@composable slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Start, tween(500)
            )
        },
        exitTransition = {
            return@composable slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.End, tween(500)
            )
        },
        popEnterTransition = {
            return@composable slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Start, tween(500)
            )
        },
        content = {
            FavoriteScreen(
                navController = navController,
            )
        }
    )
}