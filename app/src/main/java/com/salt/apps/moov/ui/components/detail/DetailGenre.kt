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

// Компонент Composable для отображения жанров фильма.
@Composable
fun DetailGenre(data: Movie, modifier: Modifier = Modifier) {
    val genres = DataMapper.mapGenreIdToGenre(data.genreIds) // Преобразует ID жанров в их названия.
    Box(
        modifier = modifier
            .fillMaxWidth() // Заполняет максимальную ширину родительского элемента.
    ) {
        LazyRow( // Создает горизонтальный скроллируемый список.
            modifier = modifier
                .align(Alignment.Center) // Центрирует список внутри Box.
        ) {
            items(genres.take(3).size) { index -> // Отображает первые три жанра из списка.
                Card( // Карточка для отображения жанра.
                    modifier = Modifier.padding(end = 10.dp), // Добавляет отступ справа.
                ) {
                    Text( // Текстовый компонент для отображения названия жанра.
                        text = genres[index] ?: "N/A", // Отображает название жанра или "N/A", если жанр неизвестен.
                        color = MaterialTheme.colorScheme.onPrimary, // Цвет текста.
                        style = MaterialTheme.typography.bodyMedium, // Стиль текста.
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.primary) // Фоновый цвет карточки.
                            .padding(
                                horizontal = 15.dp,
                                vertical = 5.dp // Внутренние отступы карточки.
                            ),
                    )
                }
            }
        }
    }
}
