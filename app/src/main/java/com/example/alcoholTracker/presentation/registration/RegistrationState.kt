package com.example.alcoholTracker.presentation.registration

data class RegistrationState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isError: Boolean = false
)