package com.example.alcoholTracker.presentation.registration

import com.example.alcoholTracker.data.Drink

data class DrinksState(
    val drinks: List<Drink>? = null,
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
    val message: String? = null
)