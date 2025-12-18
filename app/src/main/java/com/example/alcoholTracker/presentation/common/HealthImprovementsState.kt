package com.example.alcoholTracker.presentation.common

import com.example.alcoholTracker.data.HealthImprovement

data class HealthImprovementsState(
    val healthImprovements: List<HealthImprovement>? = null,
    val healthImprovement: HealthImprovement? = null,
    val daysPassed: Int? = null,
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
    val message: String? = null
)
