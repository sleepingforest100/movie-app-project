package com.salt.apps.moov.ui.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.salt.apps.moov.data.Resource
import com.salt.apps.moov.ui.components.CircularLoading
import com.salt.apps.moov.ui.components.detail.DetailGenre
import com.salt.apps.moov.ui.components.detail.DetailImage
import com.salt.apps.moov.ui.components.detail.DetailMovieRow

// Функция детального экрана, принимающая идентификатор фильма и модель представления.
@Composable
fun DetailScreen(
    movieId: Int?, // Идентификатор фильма.
    detailViewModel: DetailViewModel = hiltViewModel() // Инъекция модели представления через Hilt.
) {
    // Получение состояния детальной информации о фильме из модели представления.
    val detailMovieState by detailViewModel.detailMovie.collectAsState()

    // Запуск эффекта для загрузки данных о фильме при первом рендеринге.
    LaunchedEffect(Unit) {
        detailViewModel.getFavoriteMovies(movieId ?: 0)
    }

    // Обработка различных состояний загрузки данных о фильме.
    when (detailMovieState) {
        is Resource.Loading -> {
            CircularLoading() // Отображение индикатора загрузки.
        }

        is Resource.Success -> {
            val data = (detailMovieState as Resource.Success).data // Данные о фильме.

            // Вертикальный список с деталями фильма.
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background) // Фон экрана.
            ) {
                item {
                    DetailImage(data = data, detailViewModel = detailViewModel) // Изображение фильма.

                    Text(
                        text = data.title, // Название фильма.
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold), // Стиль текста.
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp), // Отступы.
                        textAlign = TextAlign.Center // Выравнивание текста.
                    )

                    DetailGenre(data = data) // Жанры фильма.

                    Text(
                        text = data.overview ?: "", // Описание фильма.
                        color = Color.Black,
                        lineHeight = 25.sp, // Высота линии текста.
                        modifier = Modifier.padding(20.dp), // Отступы.
                        style = MaterialTheme.typography.bodyMedium, // Стиль текста.
                        textAlign = TextAlign.Justify // Выравнивание текста.
                    )

                    DetailMovieRow(data) // Дополнительная информация о фильме.
                }
            }
        }

        is Resource.Error -> {
            // Обработка состояния ошибки (можно добавить сообщение об ошибке).
        }
    }
}
