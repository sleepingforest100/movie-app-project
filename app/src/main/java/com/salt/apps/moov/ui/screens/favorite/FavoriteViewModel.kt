package com.salt.apps.moov.ui.screens.favorite

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
// Аннотация HiltViewModel указывает, что этот класс ViewModel будет предоставлен через Hilt для внедрения зависимостей.
@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val movieRepository: MovieRepository // Внедрение зависимости репозитория фильмов.
) : ViewModel() {
    // Приватный MutableStateFlow для хранения текущего состояния списка избранных фильмов.
    private val _favoriteMovies = MutableStateFlow<Resource<List<Movie>>>(Resource.Loading())
    // Публичный геттер для StateFlow, позволяющий подписаться на обновления списка избранных фильмов.
    val favoriteMovies: StateFlow<Resource<List<Movie>>> get() = _favoriteMovies

    // Функция для загрузки списка избранных фильмов.
    fun getFavoriteMovies() = viewModelScope.launch {
        // Подписка на поток данных из репозитория и обновление состояния.
        movieRepository.getFavoriteMovies().collect {
            _favoriteMovies.value = it
        }
    }
}

