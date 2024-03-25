package com.salt.apps.moov.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.salt.apps.moov.data.model.Movie
import com.salt.apps.moov.ui.navigation.MovieScreen.DETAIL

// Компонент элемента списка фильмов с навигацией.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieListItem(
    movie: Movie, // Данные о фильме.
    navController: NavController // Контроллер для навигации.
) {
    Column {
        // Карточка с информацией о фильме.
        Card(
            // Настройка цветов карточки.
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background,
            ),
            // Обработчик клика по карточке.
            onClick = {
                // Переход на экран с детальной информацией о фильме.
                navController.navigate("${DETAIL.route}/${movie.id}")
            },
            // Модификаторы для настройки размера и отступов карточки.
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .border(
                    1.dp,
                    Color.LightGray.copy(alpha = .2f),
                    MaterialTheme.shapes.medium
                )
                .padding(horizontal = 18.dp)
        ) {
            // Горизонтальное размещение элементов внутри карточки.
            Row(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                // Контейнер для изображения.
                Box(
                    modifier = Modifier
                        .width(130.dp)
                        .height(150.dp)
                ) {
                    // Загрузчик изображения постера фильма.
                    ImageNetworkLoader(
                        imageUrl = movie.posterPath ?: "",
                        voteAverage = movie.voteAverage?.toFloat() ?: 0f,
                        modifier = Modifier
                            .width(130.dp)
                            .height(150.dp)
                    )
                }
                // Вертикальное размещение текстовой информации о фильме.
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(10.dp)
                ) {
                    // Название фильма.
                    Text(
                        text = movie.title,
                        style = TextStyle(
                            fontSize = 15.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    // Краткое описание фильма.
                    Text(
                        text = movie.overview.takeIf { it?.isNotBlank() == true }
                            ?: "N/A",
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Medium
                        )
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    // Дата выхода фильма.
                    Text(
                        text = "Release date: ${movie.releaseDate}",
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Medium
                        )
                    )
                }
            }
        }
        // Отступ между элементами списка.
        Spacer(modifier = Modifier.height(12.dp))
    }
}
