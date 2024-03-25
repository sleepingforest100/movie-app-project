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
import com.salt.apps.moov.ui.navigation.MovieScreen.DETAIL
import com.salt.apps.moov.ui.navigation.MovieScreen.FAVORITE
import com.salt.apps.moov.ui.navigation.MovieScreen.HOME
import com.salt.apps.moov.ui.screens.detail.DetailScreen
import com.salt.apps.moov.ui.screens.favorite.FavoriteScreen
import com.salt.apps.moov.ui.screens.home.HomeScreen

// Функция для настройки навигационного хоста с тремя маршрутами: домой, детали и избранное.
@Composable
fun MovieNavHost(navController: NavHostController, innerPadding: PaddingValues) {
    // Основной контейнер навигации.
    NavHost(
        navController = navController, // Контроллер навигации.
        startDestination = HOME.route, // Начальный маршрут.
        modifier = Modifier.padding(innerPadding), // Паддинг для контента.
    ) {
        homeComposable(navController = navController) // Композиция для домашнего экрана.
        detailComposable(navController = navController) // Композиция для экрана деталей.
        favoriteComposable(navController = navController) // Композиция для экрана избранного.
    }
}

// Функция для настройки маршрута домашнего экрана.
fun NavGraphBuilder.homeComposable(navController: NavHostController) {
    composable(
        route = HOME.route, // Маршрут.
        // Анимация перехода.
        enterTransition = {
            slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(500))
        },
        exitTransition = {
            slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(500))
        },
        popEnterTransition = {
            slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(500))
        },
        content = {
            HomeScreen(navController = navController) // Содержимое - домашний экран.
        }
    )
}

// Функция для настройки маршрута экрана деталей.
fun NavGraphBuilder.detailComposable(navController: NavHostController) {
    composable(
        route = "${DETAIL.route}/{movieId}", // Маршрут с параметром.
        arguments = listOf(
            navArgument("movieId") {
                type = NavType.IntType // Тип параметра.
            }
        ),
        // Анимации переходов аналогичны домашнему экрану.
        enterTransition = {
            slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(500))
        },
        exitTransition = {
            slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(500))
        },
        popEnterTransition = {
            slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(500))
        },
        content = {
            // Получение параметра из маршрута и отображение экрана деталей.
            val movieId = navController.currentBackStackEntry?.arguments?.getInt("movieId")
            DetailScreen(movieId = movieId)
        }
    )
}

// Функция для настройки маршрута экрана избранного.
fun NavGraphBuilder.favoriteComposable(navController: NavHostController) {
    composable(
        route = FAVORITE.route,
        // Анимации переходов аналогичны предыдущим.
        enterTransition = {
            slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(500))
        },
        exitTransition = {
            slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(500))
        },
        popEnterTransition = {
            slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(500))
        },
        content = {
            FavoriteScreen(navController = navController) // Содержимое - экран избранного.
        }
    )
}
