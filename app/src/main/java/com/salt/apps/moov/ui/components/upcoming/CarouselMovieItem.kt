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
import com.salt.apps.moov.ui.navigation.MovieScreen

// Аннотация для использования экспериментальных API Material 3.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarouselMovieItem(
    movie: Movie, // Данные о фильме.
    sizeScale: Float, // Масштаб элемента в карусели.
    navController: NavController // Контроллер навигации для перехода на экран деталей.
) {
    // Карточка для каждого элемента карусели.
    Card(
        shape = RoundedCornerShape(10.dp), // Скругление углов карточки.
        modifier = Modifier
            .graphicsLayer(
                scaleX = sizeScale, // Масштабирование по X.
                scaleY = sizeScale // Масштабирование по Y.
            ),
        onClick = {
            // Действие при нажатии: переход к деталям фильма.
            navController.navigate("${MovieScreen.DETAIL.route}/${movie.id}")
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth() // Заполнение максимальной ширины.
                .height(160.dp) // Высота карточки.
        ) {
            // Загрузчик изображений для отображения фона фильма.
            ImageNetworkLoader(
                imageUrl = movie.backdropPath ?: "", // URL изображения фона.
                voteAverage = movie.voteAverage?.toFloat() ?: 0f, // Средний рейтинг фильма.
                modifier = Modifier.height(160.dp), // Высота изображения.
            )
            // Прозрачный градиент в нижней части карточки.
            Spacer(
                modifier = Modifier
                    .fillMaxWidth() // Заполнение максимальной ширины.
                    .align(Alignment.BottomCenter) // Выравнивание по нижнему краю.
                    .height(50.dp) // Высота градиента.
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent, // Верхняя часть прозрачная.
                                MaterialTheme.colorScheme.scrim.copy(alpha = .6f) // Нижняя часть с заливкой.
                            ),
                        ),
                    )
            )
            // Текст с названием фильма.
            Text(
                text = movie.title, // Название фильма.
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold, // Жирное начертание.
                    color = MaterialTheme.colorScheme.onPrimary // Цвет текста.
                ),
                textAlign = TextAlign.Center, // Выравнивание текста по центру.
                modifier = Modifier
                    .fillMaxWidth() // Заполнение максимальной ширины.
                    .align(Alignment.BottomCenter) // Выравнивание по нижнему краю.
                    .padding(10.dp) // Отступы вокруг текста.
            )
        }
    }
}
