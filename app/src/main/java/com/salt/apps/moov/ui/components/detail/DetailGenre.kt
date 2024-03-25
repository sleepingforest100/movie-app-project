package com.salt.apps.moov.ui.components.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.salt.apps.moov.data.model.Movie
import com.salt.apps.moov.utilities.DataMapper

@Composable
fun DetailGenre(data: Movie, modifier: Modifier = Modifier) {
    val genres = DataMapper.mapGenreIdToGenre(data.genreIds)
    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        LazyRow(
            modifier = modifier
                .align(Alignment.Center)
        ) {
            items(genres.take(3).size) { index ->
                Card(
                    modifier = Modifier.padding(end = 10.dp),
                ) {
                    Text(
                        text = genres[index] ?: "N/A",
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.primary)
                            .padding(
                                horizontal = 15.dp,
                                vertical = 5.dp
                            ),
                    )
                }
            }
        }
    }
}