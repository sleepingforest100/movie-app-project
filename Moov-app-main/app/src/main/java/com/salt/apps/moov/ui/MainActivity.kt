package com.salt.apps.moov.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.salt.apps.moov.ui.components.main.MoovApp
import com.salt.apps.moov.ui.theme.MoovTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private var isSplashScreenClosed = false
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().setKeepOnScreenCondition {
            !isSplashScreenClosed
        }
        super.onCreate(savedInstanceState)
        setContent {
            MoovTheme {
                LaunchedEffect(key1 = Unit) {
                    delay(2000)
                    isSplashScreenClosed = true
                }
                MoovApp()
            }
        }
    }
}