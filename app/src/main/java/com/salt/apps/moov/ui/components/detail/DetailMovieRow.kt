package com.salt.apps.moov.ui.components.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.salt.apps.moov.data.model.Movie

// Компонент для отображения строки с деталями фильма.
@Composable
fun DetailMovieRow(data: Movie) {
    Row(
        modifier = Modifier.fillMaxWidth(), // Заполнение максимальной ширины.
        horizontalArrangement = Arrangement.SpaceEvenly // Равномерное распределение колонок по горизонтали.
    ) {
        // Колонка для даты выпуска.
        DetailColumn("Release date", data.releaseDate ?: "N/A")
        // Колонка для языка.
        DetailColumn("Language", data.originalLanguage)
        // Колонка для количества голосов.
        DetailColumn("Votes", data.voteCount.toString())
    }
}

// Компонент для отображения отдельной колонки с информацией.
@Composable
fun DetailColumn(title: String, value: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally, // Выравнивание по центру по горизонтали.
        verticalArrangement = Arrangement.spacedBy(4.dp) // Расстояние между элементами в колонке.
    ) {
        // Заголовок колонки.
        Text(title, style = MaterialTheme.typography.bodyMedium)
        // Значение, связанное с заголовком.
        Text(value, style = MaterialTheme.typography.bodyMedium)
    }
}

