package com.salt.apps.moov.ui.components.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.salt.apps.moov.data.model.Movie
import com.salt.apps.moov.ui.components.ImageNetworkLoader
import com.salt.apps.moov.ui.screens.detail.DetailViewModel

@Composable
fun DetailImage(data: Movie, detailViewModel: DetailViewModel) {
    var isFavorite by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .height(340.dp)
    ) {
        Box {
            ImageNetworkLoader(
                imageUrl = data.backdropPath ?: "",
                voteAverage = 0f,
                showVoteAverage = false,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .height(100.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.White
                            ),
                        ),
                    )
            )

        }
        Box(
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Card {
                ImageNetworkLoader(
                    imageUrl = data.posterPath ?: "",
                    voteAverage = data.voteAverage?.toFloat() ?: 0f,
                    modifier = Modifier
                        .width(150.dp)
                        .height(200.dp)
                )
            }

            Card(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
            ) {
                ToggleFavorite(
                    isFavorite = data.isFavorite,
                    onToggle = {
                        isFavorite = it
                        detailViewModel.toggleFavorite(data.id, isFavorite)
                    }, modifier = Modifier
                        .size(45.dp)
                        .background(
                            color = MaterialTheme.colorScheme.background,
                        )
                )
            }
        }
    }

}
