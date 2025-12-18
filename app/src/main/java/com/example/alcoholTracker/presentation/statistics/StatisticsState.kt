package com.example.alcoholTracker.presentation.statistics

import com.example.alcoholTracker.data.AvoidedDrinks
import com.example.alcoholTracker.data.SavedMoney

data class StatisticsState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    var isError: Boolean = false,
    val message: String? = null,
    val avoidedDrinks: AvoidedDrinks? = null,
    val savedMoney: SavedMoney? = null
)
