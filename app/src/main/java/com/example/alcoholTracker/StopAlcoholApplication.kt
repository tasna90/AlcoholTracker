package com.example.alcoholTracker

import android.app.Application
import androidx.compose.runtime.staticCompositionLocalOf
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AlcoholTrackerApplication: Application()

val LocalIsDarkTheme = staticCompositionLocalOf { false }