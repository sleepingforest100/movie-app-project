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

@Composable
fun FavoriteScreen(
    navController: NavController,
    favoriteViewModel: FavoriteViewModel = hiltViewModel()
) {
    val favoriteMoviesState by favoriteViewModel.favoriteMovies.collectAsState()

    LaunchedEffect(Unit) {
        favoriteViewModel.getFavoriteMovies()
    }

    when (favoriteMoviesState) {
        is Resource.Loading -> {
            CircularLoading()
        }

        is Resource.Success -> {
            val data = (favoriteMoviesState as Resource.Success).data
            if (data.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "No favorite movies available",
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    items(data.size) { index ->
                        MovieListItem(movie = data[index], navController = navController)
                    }
                }
            }
        }

        is Resource.Error -> {}
    }
}