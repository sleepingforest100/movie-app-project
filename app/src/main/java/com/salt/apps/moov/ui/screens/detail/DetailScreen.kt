package com.salt.apps.moov.ui.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.salt.apps.moov.data.Resource
import com.salt.apps.moov.ui.components.CircularLoading
import com.salt.apps.moov.ui.components.detail.DetailGenre
import com.salt.apps.moov.ui.components.detail.DetailImage
import com.salt.apps.moov.ui.components.detail.DetailMovieRow

@Composable
fun DetailScreen(
    movieId: Int?,
    detailViewModel: DetailViewModel = hiltViewModel()
) {
    val detailMovieState by detailViewModel.detailMovie.collectAsState()

    LaunchedEffect(Unit) {
        detailViewModel.getFavoriteMovies(movieId ?: 0)
    }

    when (detailMovieState) {
        is Resource.Loading -> {
            CircularLoading()
        }

        is Resource.Success -> {
            val data = (detailMovieState as Resource.Success).data

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
            ) {
                item {
                    DetailImage(data = data, detailViewModel = detailViewModel)

                    Text(
                        data.title,
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        textAlign = TextAlign.Center
                    )

                    DetailGenre(data = data)

                    Text(
                        text = data.overview ?: "",
                        color = Color.Black,
                        lineHeight = 25.sp,
                        modifier = Modifier.padding(20.dp),
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Justify
                    )

                    DetailMovieRow(data)
                }
            }

//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .background(MaterialTheme.colorScheme.background)
//            ) {
//                DetailImage(data = data, detailViewModel = detailViewModel)
//
//                Text(
//                    data.title,
//                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(20.dp),
//                    textAlign = TextAlign.Center
//                )
//
//                DetailGenre(data = data)
//
//                Text(
//                    text = data.overview ?: "",
//                    color = Color.Black,
//                    lineHeight = 25.sp,
//                    modifier = Modifier.padding(20.dp),
//                    style = MaterialTheme.typography.bodyMedium,
//                    textAlign = TextAlign.Justify
//                )
//
//                DetailMovieRow(data)
//            }
        }

        is Resource.Error -> {}
    }
}