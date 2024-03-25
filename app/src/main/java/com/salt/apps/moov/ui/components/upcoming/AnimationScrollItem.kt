package com.salt.apps.moov.ui.components.upcoming

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AnimationScrollItem(pagerState: PagerState) {
    LaunchedEffect(key1 = pagerState.currentPage) {
        launch {
            while (true) {
                delay(3000)
                withContext(NonCancellable) {
                    if (pagerState.currentPage + 1 in 0..Int.MAX_VALUE) {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    } else {
                        val initPage = Int.MAX_VALUE / 2
                        pagerState.animateScrollToPage(initPage)
                    }
                }
            }
        }
    }
}