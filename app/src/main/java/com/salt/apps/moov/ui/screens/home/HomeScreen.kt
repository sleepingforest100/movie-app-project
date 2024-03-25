package com.salt.apps.moov.ui.screens.home


import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.salt.apps.moov.data.Resource
import com.salt.apps.moov.data.model.Movie
import com.salt.apps.moov.ui.components.MovieListItem
import com.salt.apps.moov.ui.components.upcoming.AnimationScrollItem
import com.salt.apps.moov.ui.components.upcoming.CarouselMovieItem

// Домашний экран приложения, отображающий списки предстоящих и популярных фильмов.
@Composable
fun HomeScreen(
    navController: NavController, // Контроллер навигации для перехода к деталям фильма.
    homeViewModel: HomeViewModel = hiltViewModel() // Модель представления для загрузки данных о фильмах.
) {
    // Подписка на состояние предстоящих и популярных фильмов.
    val upcomingMoviesState by homeViewModel.upcomingMoviesState.collectAsState()
    val popularMoviesState by homeViewModel.popularMoviesState.collectAsState()

    // Вертикальный список для отображения разделов с фильмами.
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background) // Фон экрана.
    ) {
        item {
            // Заголовок раздела предстоящих фильмов.
            Text(
                text = "Upcoming Movies",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(start = 18.dp, bottom = 15.dp)
            )
            // Обработка состояния и отображение списка предстоящих фильмов.
            HandleSectionMoviesState(
                upcomingMoviesState = upcomingMoviesState,
                navController = navController
            )
        }

        item {
            // Заголовок раздела популярных фильмов.
            Text(
                text = "Popular Movies",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(start = 18.dp, bottom = 15.dp, top = 20.dp)
            )
        }

        // Обработка состояния и отображение списка популярных фильмов.
        handlePopularMoviesState(
            popularMoviesState = popularMoviesState,
            navController = navController
        )
    }
}


//HandleSectionMoviesState и handlePopularMoviesState
// функции отвечают за отображение фильмов в зависимости от их
// состояния: загрузка, успех или ошибка. В случае успеха,
// для предстоящих фильмов используется горизонтальный карусельный
// список (HorizontalPager), а для популярных фильмов —
// вертикальный список (LazyColumn).

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HandleSectionMoviesState(
    upcomingMoviesState: Resource<List<Movie>>,
    navController: NavController
) {
    val pageCount = Int.MAX_VALUE
    val pagerState = rememberPagerState(
        pageCount = { pageCount },
        initialPage = pageCount / 2,
    )

    when (upcomingMoviesState) {
        is Resource.Loading -> {}

        is Resource.Success -> {
            val movies = upcomingMoviesState.data

            CompositionLocalProvider(LocalOverscrollConfiguration provides null) {
                HorizontalPager(
                    modifier = Modifier.height(160.dp),
                    state = pagerState,
                    contentPadding = PaddingValues(horizontal = 60.dp),
                ) { page ->
                    val sizeScale by animateFloatAsState(
                        if (pagerState.currentPage == page) 1f else .85f,
                        label = "",
                        animationSpec = tween(
                            durationMillis = 250,
                        )
                    )

                    CarouselMovieItem(
                        movie = movies[page % movies.size],
                        sizeScale = sizeScale,
                        navController = navController
                    )
                }
            }

            AnimationScrollItem(pagerState)
        }

        is Resource.Error -> {}
    }
}

fun LazyListScope.handlePopularMoviesState(
    popularMoviesState: Resource<List<Movie>>,
    navController: NavController
) {
    when (popularMoviesState) {
        is Resource.Loading -> {}

        is Resource.Success -> {
            val movies = popularMoviesState.data

            items(movies.size) { index ->
                MovieListItem(movie = movies[index], navController = navController)
            }

        }

        is Resource.Error -> {}
    }
}