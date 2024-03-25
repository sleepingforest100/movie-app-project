package com.salt.apps.moov.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// Компонент для отображения кругового индикатора загрузки.
@Composable
fun CircularLoading() {
    Box(
        modifier = Modifier
            .fillMaxSize() // Заполняет весь доступный размер экрана.
            .wrapContentSize(Alignment.Center) // Размещает содержимое по центру.
    ) {
        // Круговой индикатор загрузки.
        CircularProgressIndicator(
            strokeWidth = 3.dp, // Ширина линии индикатора.
            color = MaterialTheme.colorScheme.primary, // Цвет индикатора, основанный на текущей теме.
            modifier = Modifier.size(20.dp) // Размер индикатора.
        )
    }
}
