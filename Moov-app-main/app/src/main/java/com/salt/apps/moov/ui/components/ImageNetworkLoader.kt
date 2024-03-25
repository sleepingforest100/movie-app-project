package com.salt.apps.moov.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import com.salt.apps.moov.utilities.Constants

@Composable
fun ImageNetworkLoader(
    imageUrl: String,
    voteAverage: Float,
    modifier: Modifier = Modifier,
    showVoteAverage: Boolean = true,
) {
    Box {
        SubcomposeAsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(Constants.getImageUrl(imageUrl))
                .crossfade(true)
                .crossfade(400)
                .build(),
            contentDescription = null,
            modifier = modifier
        ) {
            when (painter.state) {
                is AsyncImagePainter.State.Loading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(Alignment.Center)
                    ) {
                        CircularProgressIndicator(
                            strokeWidth = 3.dp,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }

                is AsyncImagePainter.State.Error -> {

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(Alignment.Center)
                    ) {
                        Text(
                            "IMAGE URL NOT FOUND",
                            fontWeight = FontWeight.Black
                        )
                    }
                }

                else -> {
                    SubcomposeAsyncImageContent(
                        contentScale = ContentScale.Crop,
                    )
                }
            }
        }

        if (showVoteAverage) {
            Box(modifier = Modifier.padding(10.dp)) {
                CircularVote(percentage = voteAverage)
            }
        }
    }

}