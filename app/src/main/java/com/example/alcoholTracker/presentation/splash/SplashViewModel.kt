package com.example.alcoholTracker.presentation.splash

import androidx.lifecycle.ViewModel
import com.example.alcoholTracker.domain.use_case.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase
): ViewModel() {


}