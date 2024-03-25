package com.salt.apps.moov.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.salt.apps.moov.data.Resource
import com.salt.apps.moov.data.model.Movie
import com.salt.apps.moov.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

// Аннотация HiltViewModel указывает, что этот класс ViewModel будет предоставлен с помощью Hilt для внедрения зависимостей.
@HiltViewModel
class DetailViewModel @Inject constructor(
    private val movieRepository: MovieRepository // Внедрение зависимости репозитория фильмов.
) : ViewModel() {

    // Приватный MutableStateFlow для хранения состояния загрузки деталей фильма.
    private val _detailMovie = MutableStateFlow<Resource<Movie>>(Resource.Loading())
    // Публичный геттер для StateFlow, через который UI может подписаться на изменения состояния.
    val detailMovie: StateFlow<Resource<Movie>> get() = _detailMovie

    // Функция для загрузки данных о фильме по его ID.
    fun getFavoriteMovies(movieId: Int) = viewModelScope.launch {
        // Подписка на поток данных из репозитория и обновление состояния.
        movieRepository.getMovieById(movieId).collect {
            _detailMovie.value = it
        }
    }

    // Функция для изменения статуса "избранное" у фильма.
    fun toggleFavorite(movieId: Int, isFavorite: Boolean) = viewModelScope.launch {
        // Вызов метода репозитория для обновления статуса фильма.
        movieRepository.updateMovieById(movieId, isFavorite)
    }
}
