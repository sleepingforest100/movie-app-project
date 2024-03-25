package com.salt.apps.moov.ui.components.main

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.salt.apps.moov.ui.navigation.MovieNavHost
import com.salt.apps.moov.ui.navigation.MovieScreen.DETAIL
import com.salt.apps.moov.ui.navigation.MovieScreen.FAVORITE
import com.salt.apps.moov.ui.navigation.MovieScreen.HOME

// Основной компонент приложения, управляющий навигацией.
@Composable
fun MovieApp(
    navController: NavHostController = rememberNavController() // Создание контроллера навигации.
) {
    val backStackEntry by navController.currentBackStackEntryAsState() // Текущая запись стека навигации.
    // Определение текущего экрана на основе маршрута.
    val currentScreen = backStackEntry?.destination?.route?.let { route ->
        when (route) {
            HOME.route -> HOME // Домашний экран.
            "${DETAIL.route}/{movieId}" -> DETAIL // Экран с деталями фильма.
            FAVORITE.route -> FAVORITE // Экран избранного.
            else -> null
        }
    } ?: HOME // Возврат на домашний экран, если маршрут не распознан.

    Scaffold(
        topBar = {
            // Верхняя панель приложения.
            MovieTopAppBar(
                currentScreen = currentScreen, // Текущий экран для отображения в панели.
                canNavigateBack = navController.previousBackStackEntry != null, // Наличие возможности возврата назад.
                navigateUp = {
                    navController.navigateUp() // Функция для навигации назад.
                },
                onFavoriteClicked = {
                    navController.navigate(FAVORITE.route) // Переход на экран избранного.
                }
            )
        },
        content = { innerPadding ->
            // Контент приложения, содержащий навигационный компонент.
            MovieNavHost(navController = navController, innerPadding = innerPadding)
        }
    )
}
