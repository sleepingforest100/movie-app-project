package com.salt.apps.moov.ui.screens.favorite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.salt.apps.moov.data.Resource
import com.salt.apps.moov.ui.components.CircularLoading
import com.salt.apps.moov.ui.components.MovieListItem

// Экран избранных фильмов.
@Composable
fun FavoriteScreen(
    navController: NavController, // Контроллер навигации для перехода между экранами.
    favoriteViewModel: FavoriteViewModel = hiltViewModel() // Внедрение экземпляра FavoriteViewModel.
) {
    // Подписка на состояние списка избранных фильмов.
    val favoriteMoviesState by favoriteViewModel.favoriteMovies.collectAsState()

    // Запуск эффекта для инициализации загрузки списка избранных фильмов.
    LaunchedEffect(Unit) {
        favoriteViewModel.getFavoriteMovies()
    }

    // Отображение состояния загрузки, успешной загрузки или ошибки.
    when (favoriteMoviesState) {
        // Отображение индикатора загрузки.
        is Resource.Loading -> {
            CircularLoading()
        }

        // Отображение списка избранных фильмов.
        is Resource.Success -> {
            val data = (favoriteMoviesState as Resource.Success).data
            if (data.isEmpty()) {
                // Сообщение о пустом списке избранных фильмов.
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "No favorite movies available", // Сообщение пользователю.
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            } else {
                // Отображение списка избранных фильмов.
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    items(data.size) { index ->
                        MovieListItem(movie = data[index], navController = navController) // Элемент списка фильма.
                    }
                }
            }
        }

        // Обработка ошибки загрузки.
        is Resource.Error -> {
            // Здесь может быть отображено сообщение об ошибке.
        }
    }
}
