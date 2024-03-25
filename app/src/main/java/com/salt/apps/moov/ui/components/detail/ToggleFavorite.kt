package com.salt.apps.moov.ui.components.detail

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

// Компонент для переключения состояния "в избранном".
@Composable
fun ToggleFavorite(
    modifier: Modifier = Modifier, // Модификатор для настройки внешнего вида и расположения кнопки.
    onToggle: (Boolean) -> Unit, // Функция обратного вызова, вызываемая при изменении состояния.
    isFavorite: Boolean = false // Начальное состояние, указывающее, находится ли элемент в избранном.
) {
    var isChecked by remember { mutableStateOf(isFavorite) } // Состояние, отслеживающее, находится ли элемент в избранном.

    // Кнопка для переключения состояния.
    IconToggleButton(
        modifier = modifier, // Применение переданного модификатора к кнопке.
        checked = isChecked, // Текущее состояние кнопки.
        onCheckedChange = {
            isChecked = it // Обновление состояния при нажатии.
            onToggle(it) // Вызов функции обратного вызова с новым состоянием.
        }
    ) {
        // Выбор иконки в зависимости от состояния.
        val icon = if (isChecked) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder
        // Установка цвета иконки. Здесь используется один и тот же цвет для обоих состояний, но это можно изменить.
        val iconTint = if (isChecked) Color.Red else Color.Red

        // Отображение иконки.
        Icon(
            imageVector = icon, // Векторное изображение иконки.
            contentDescription = null, // Описание иконки для доступности. Здесь оно не задано.
            tint = iconTint, // Цвет иконки.
        )
    }
}
