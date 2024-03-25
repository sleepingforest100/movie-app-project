package com.salt.apps.moov.ui.components.upcoming

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// аннотация для доступа к экспериментальным API Foundation.
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AnimationScrollItem(pagerState: PagerState) {
    // LaunchedEffect запускается при изменении currentPage в pagerState.
    LaunchedEffect(key1 = pagerState.currentPage) {
        // Создание корутины внутри LaunchedEffect.
        launch {
            // Бесконечный цикл для создания эффекта автоматической прокрутки.
            while (true) {
                delay(3000) // Задержка между прокрутками.
                // Блокировка отмены для гарантии выполнения анимации.
                withContext(NonCancellable) {
                    if (pagerState.currentPage + 1 in 0..Int.MAX_VALUE) {
                        // Анимация прокрутки к следующей странице, если она существует.
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    } else {
                        // Возврат к начальной странице, если достигнут конец.
                        val initPage = Int.MAX_VALUE / 2
                        pagerState.animateScrollToPage(initPage)
                    }
                }
            }
        }
    }
}
