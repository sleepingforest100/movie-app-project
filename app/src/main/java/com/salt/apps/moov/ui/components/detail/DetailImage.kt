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

// Компонент Composable для отображения детального изображения фильма и иконки избранного.
@Composable
fun DetailImage(data: Movie, detailViewModel: DetailViewModel) {
    var isFavorite by remember { mutableStateOf(false) } // Состояние, отслеживающее, является ли фильм избранным.
    Box(
        modifier = Modifier
            .height(340.dp) // Установка высоты контейнера.
    ) {
        Box {
            // Загрузчик сетевого изображения для фона фильма.
            ImageNetworkLoader(
                imageUrl = data.backdropPath ?: "", // URL изображения фона. Если отсутствует, используется пустая строка.
                voteAverage = 0f, // Средний рейтинг не отображается.
                showVoteAverage = false, // Отключение отображения рейтинга.
                modifier = Modifier
                    .fillMaxWidth() // Заполнение максимальной ширины.
                    .height(250.dp) // Установка высоты изображения.
            )

            // Пространство с градиентом в нижней части изображения для визуального эффекта перехода.
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter) // Выравнивание по нижнему центру.
                    .height(100.dp) // Высота пространства.
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent, // Начало градиента прозрачное.
                                Color.White // Конец градиента белый.
                            ),
                        ),
                    )
            )

        }
        Box(
            modifier = Modifier.align(Alignment.BottomCenter) // Выравнивание по нижнему центру.
        ) {
            // Карточка для постера фильма.
            Card {
                ImageNetworkLoader(
                    imageUrl = data.posterPath ?: "", // URL постера фильма. Если отсутствует, используется пустая строка.
                    voteAverage = data.voteAverage?.toFloat() ?: 0f, // Средний рейтинг фильма, преобразованный в Float.
                    modifier = Modifier
                        .width(150.dp) // Ширина постера.
                        .height(200.dp) // Высота постера.
                )
            }

            // Карточка для иконки избранного.
            Card(
                modifier = Modifier
                    .align(Alignment.BottomEnd) // Выравнивание по нижнему правому углу.
            ) {
                // Компонент переключателя избранного.
                ToggleFavorite(
                    isFavorite = data.isFavorite, // Текущее состояние избранного.
                    onToggle = {
                        isFavorite = it // Обновление состояния избранного.
                        detailViewModel.toggleFavorite(data.id, isFavorite) // Обновление состояния избранного во ViewModel.
                    }, modifier = Modifier
                        .size(45.dp) // Размер иконки избранного.
                        .background(
                            color = MaterialTheme.colorScheme.background, // Фоновый цвет иконки.
                        )
                )
            }
        }
    }

}
