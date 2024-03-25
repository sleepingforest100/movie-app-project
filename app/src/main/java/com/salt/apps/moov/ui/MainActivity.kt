package com.salt.apps.moov.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.salt.apps.moov.ui.components.main.MovieApp
import com.salt.apps.moov.ui.theme.MoovTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

// Главная активность приложения, отвечающая за отображение заставки и основного UI.
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    // Флаг для отслеживания состояния заставки.
    private var isSplashScreenClosed = false

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {

                // Основной компонент UI приложения.
                MovieApp()
            }
        }
    }


