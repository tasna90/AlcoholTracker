package com.example.alcoholTracker.presentation.common

import com.example.alcoholTracker.data.Milestone

data class MilestonesState(
    val milestones: List<Milestone>? = null,
    val milestone: Milestone? = null,
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
    val message: String? = null
)