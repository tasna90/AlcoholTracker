package com.example.alcoholTracker.presentation.home

import com.example.alcoholTracker.data.User

data class HomeState(
    val user: User? = null,
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
    val message: String? = null
)