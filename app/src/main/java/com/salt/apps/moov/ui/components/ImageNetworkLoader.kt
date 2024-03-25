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

// Компонент для асинхронной загрузки изображения из сети с отображением индикатора загрузки.
@Composable
fun ImageNetworkLoader(
    imageUrl: String, // URL изображения для загрузки.
    voteAverage: Float, // Средний рейтинг для отображения, если требуется.
    modifier: Modifier = Modifier, // Модификатор для настройки внешнего вида изображения.
    showVoteAverage: Boolean = true, // Флаг, указывающий, нужно ли показывать средний рейтинг.
) {
    Box {
        // Компонент для асинхронной загрузки изображения с поддержкой состояний загрузки.
        SubcomposeAsyncImage(
            model = ImageRequest.Builder(LocalContext.current) // Создание запроса на загрузку изображения.
                .data(Constants.getImageUrl(imageUrl)) // Установка URL изображения.
                .crossfade(true) // Анимация плавного появления изображения.
                .crossfade(400) // Продолжительность анимации плавного появления.
                .build(),
            contentDescription = null, // Описание содержимого изображения для доступности.
            modifier = modifier // Применение модификатора к изображению.
        ) {
            // Обработка состояний загрузки изображения.
            when (painter.state) {
                // Состояние загрузки.
                is AsyncImagePainter.State.Loading -> {
                    // Отображение индикатора загрузки по центру.
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(Alignment.Center)
                    ) {
                        CircularProgressIndicator(
                            strokeWidth = 3.dp,
                            color = MaterialTheme.colorScheme.primary, // Цвет индикатора.
                            modifier = Modifier.size(20.dp) // Размер индикатора.
                        )
                    }
                }

                // Состояние ошибки загрузки.
                is AsyncImagePainter.State.Error -> {
                    // Отображение текста ошибки, если изображение не найдено.
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(Alignment.Center)
                    ) {
                        Text(
                            "IMAGE URL NOT FOUND", // Текст ошибки.
                            fontWeight = FontWeight.Black // Жирное начертание текста.
                        )
                    }
                }

                // Состояние успешной загрузки.
                else -> {
                    // Отображение содержимого изображения.
                    SubcomposeAsyncImageContent(
                        contentScale = ContentScale.Crop, // Масштабирование содержимого.
                    )
                }
            }
        }

        // Опциональное отображение среднего рейтинга в виде кругового индикатора.
        if (showVoteAverage) {
            // Размещение кругового индикатора с рейтингом внутри контейнера.
            Box(modifier = Modifier.padding(10.dp)) {
                CircularVote(percentage = voteAverage)
            }
        }
    }
}
