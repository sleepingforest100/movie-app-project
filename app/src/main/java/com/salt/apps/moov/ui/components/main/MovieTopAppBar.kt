package com.salt.apps.moov.ui.components.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.salt.apps.moov.ui.navigation.MovieScreen

// Аннотация для использования экспериментального API Material 3.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieTopAppBar(
    currentScreen: MovieScreen, // Текущий экран приложения.
    canNavigateBack: Boolean, // Указывает, можно ли перейти назад.
    navigateUp: () -> Unit, // Функция для навигации назад.
    onFavoriteClicked: () -> Unit, // Функция, вызываемая при нажатии на избранное.
    modifier: Modifier = Modifier // Модификатор для настройки внешнего вида.
) {
    TopAppBar(
        title = {
            // Название текущего экрана, отображаемое в верхней панели.
            Text(
                text = currentScreen.route, // Маршрут текущего экрана в качестве названия.
                style = MaterialTheme.typography.titleLarge // Стиль текста.
            )
        },
        // Настройка цветов верхней панели.
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background // Цвет фона.
        ),
        modifier = modifier, // Применение внешнего модификатора.
        // Иконка навигации, отображаемая, если можно перейти назад.
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack, // Иконка стрелки назад.
                        contentDescription = "Back" // Описание для доступности.
                    )
                }
            }
        },
        // Действия верхней панели. Иконка избранного отображается, если навигация назад невозможна.
        actions = {
            if (!canNavigateBack) {
                IconButton(onClick = onFavoriteClicked) {
                    Icon(
                        imageVector = Icons.Filled.FavoriteBorder, // Иконка избранного.
                        contentDescription = "Favorite", // Описание для доступности.
                        tint = Color.Red // Цвет иконки.
                    )
                }
            }
        },
    )
}
