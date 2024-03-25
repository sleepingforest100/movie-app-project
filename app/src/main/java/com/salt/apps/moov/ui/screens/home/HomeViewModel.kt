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

@HiltViewModel
class HomeViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {

    private val _popularMoviesState = MutableStateFlow<Resource<List<Movie>>>(Resource.Loading())
    val popularMoviesState: StateFlow<Resource<List<Movie>>> get() = _popularMoviesState

    private val _upcomingMoviesState = MutableStateFlow<Resource<List<Movie>>>(Resource.Loading())
    val upcomingMoviesState: StateFlow<Resource<List<Movie>>> get() = _upcomingMoviesState

    init {
        fetchPopularMovies()
        fetchUpcomingMovies()
    }

    private fun fetchPopularMovies() = viewModelScope.launch {
        movieRepository.getPopularMovies().collect { result ->
            _popularMoviesState.value = result
        }
    }

    private fun fetchUpcomingMovies() = viewModelScope.launch {
        movieRepository.getUpcomingMovies().collect { result ->
            _upcomingMoviesState.value = result
        }
    }
}