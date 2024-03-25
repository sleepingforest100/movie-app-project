package com.salt.apps.moov.ui.components.upcoming

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.salt.apps.moov.data.model.Movie
import com.salt.apps.moov.ui.components.ImageNetworkLoader
import com.salt.apps.moov.ui.navigation.MoovScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarouselMovieItem(
    movie: Movie,
    sizeScale: Float,
    navController: NavController
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .graphicsLayer(
                scaleX = sizeScale,
                scaleY = sizeScale
            ),
        onClick = {
            navController.navigate("${MoovScreen.DETAIL.route}/${movie.id}")
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
        ) {
            ImageNetworkLoader(
                imageUrl = movie.backdropPath ?: "",
                voteAverage = movie.voteAverage?.toFloat() ?: 0f,
                modifier = Modifier.height(160.dp),
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .height(50.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                MaterialTheme.colorScheme.scrim.copy(alpha = .6f)
                            ),
                        ),
                    )
            )
            Text(
                movie.title,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(10.dp)
            )
        }
    }
}