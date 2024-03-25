package com.salt.apps.moov.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

// Компонент для отображения кругового индикатора с процентом голосов.
@Composable
fun CircularVote(
    percentage: Float, // Процент заполнения.
    radius: Dp = 15.dp, // Радиус индикатора.
    color: Color = MaterialTheme.colorScheme.surfaceVariant, // Цвет заполнения.
    strokeWidth: Dp = 2.dp, // Толщина линии индикатора.
    animDuration: Int = 1000, // Продолжительность анимации в миллисекундах.
    animDelay: Int = 0 // Задержка начала анимации в миллисекундах.
) {
    var animationPlayed by remember {
        mutableStateOf(false) // Флаг, указывающий, была ли проиграна анимация.
    }
    val currentPercentage = animateFloatAsState(
        targetValue = if (animationPlayed) percentage else 0f, // Целевое значение анимации.
        animationSpec = tween(
            durationMillis = animDuration, // Продолжительность анимации.
            delayMillis = animDelay // Задержка анимации.
        ), label = ""
    )

    // Запуск анимации при первом рендеринге компонента.
    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(radius * 2f) // Установка размера контейнера для индикатора.
    ) {
        val circleBgColor = MaterialTheme.colorScheme.scrim // Цвет фона круга.
        // Отрисовка кругового индикатора и заполненной части.
        Canvas(modifier = Modifier.size(radius * 2f)) {
            drawCircle(
                color = circleBgColor,
                alpha = 0.6f // Прозрачность фона круга.
            )
            drawArc(
                color = color, // Цвет заполнения.
                startAngle = -90f, // Начальный угол.
                sweepAngle = 360 * (currentPercentage.value / 10), // Угол заполнения.
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round) // Стиль линии.
            )
        }
        // Текст с текущим процентом голосов.
        Text(
            text = "%.1f".format(currentPercentage.value),
            color = MaterialTheme.colorScheme.surfaceVariant, // Цвет текста.
            style = MaterialTheme.typography.bodySmall // Стиль текста.
        )
    }
}
