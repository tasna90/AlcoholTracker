package com.example.alcoholTracker.presentation.profile

import androidx.lifecycle.ViewModel
import com.example.alcoholTracker.common.Resource
import com.example.alcoholTracker.domain.use_case.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    val getUserUseCase: GetUserUseCase
): ViewModel() {


    init {
        getUser()
    }

    private fun getUser() {
        getUserUseCase.invoke().onEach {
            when (it) {
                is Resource.Success -> {}
                is Resource.Loading -> {}
                is Resource.Failure -> {}
            }
        }
    }
}