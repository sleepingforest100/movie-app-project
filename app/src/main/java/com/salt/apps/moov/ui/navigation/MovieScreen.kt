package com.salt.apps.moov.ui.navigation

// Запечатанный класс для представления различных экранов приложения и их маршрутов.
sealed class MovieScreen(val route: String) {
    // Объекты, представляющие каждый экран приложения с соответствующим маршрутом.
    data object HOME : MovieScreen("Home") // Домашний экран.
    data object DETAIL : MovieScreen("Detail") // Экран детальной информации.
    data object FAVORITE : MovieScreen("Favorite") // Экран избранных элементов.
}
