package com.salt.apps.moov.ui.screens.home

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

// ViewModel для домашнего экрана, управляющий загрузкой данных о популярных и предстоящих фильмах.
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository // Внедрение зависимости репозитория фильмов.
) : ViewModel() {

    // Состояние списка популярных фильмов с начальным состоянием загрузки.
    private val _popularMoviesState = MutableStateFlow<Resource<List<Movie>>>(Resource.Loading())
    val popularMoviesState: StateFlow<Resource<List<Movie>>> get() = _popularMoviesState

    // Состояние списка предстоящих фильмов с начальным состоянием загрузки.
    private val _upcomingMoviesState = MutableStateFlow<Resource<List<Movie>>>(Resource.Loading())
    val upcomingMoviesState: StateFlow<Resource<List<Movie>>> get() = _upcomingMoviesState

    // Инициализация ViewModel с загрузкой данных о фильмах.
    init {
        fetchPopularMovies() // Загрузка данных о популярных фильмах.
        fetchUpcomingMovies() // Загрузка данных о предстоящих фильмах.
    }

    // Загрузка данных о популярных фильмах и обновление состояния.
    private fun fetchPopularMovies() = viewModelScope.launch {
        movieRepository.getPopularMovies().collect { result ->
            _popularMoviesState.value = result // Обновление состояния популярных фильмов.
        }
    }

    // Загрузка данных о предстоящих фильмах и обновление состояния.
    private fun fetchUpcomingMovies() = viewModelScope.launch {
        movieRepository.getUpcomingMovies().collect { result ->
            _upcomingMoviesState.value = result // Обновление состояния предстоящих фильмов.
        }
    }
}
