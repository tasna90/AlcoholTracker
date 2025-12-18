package com.example.alcoholTracker.presentation.health_improvements

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alcoholTracker.common.Resource
import com.example.alcoholTracker.domain.use_case.GetHealthImprovementByIdUseCase
import com.example.alcoholTracker.presentation.common.HealthImprovementsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HealthImprovementsDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getHealthImprovementByIdUseCase: GetHealthImprovementByIdUseCase
): ViewModel() {
    private val daysPassed: String = checkNotNull(savedStateHandle["daysPassed"])
    private val healthImprovementId: String = checkNotNull(savedStateHandle["healthImprovementId"])

    private val _state = mutableStateOf(HealthImprovementsState(isLoading = true))
    val state: State<HealthImprovementsState> = _state

    init {
        getHealthImprovementById(healthImprovementId.toInt())
    }

    private fun getHealthImprovementById(id: Int) {
        getHealthImprovementByIdUseCase.invoke(id).onEach {
            when(it) {
                is Resource.Success -> {
                    _state.value = HealthImprovementsState(
                        healthImprovement = it.data,
                        daysPassed = daysPassed.toInt(),
                        isSuccess = true
                    )
                }
                is Resource.Loading -> {
                    _state.value = HealthImprovementsState(
                        isLoading = true
                    )
                }
                is Resource.Failure -> {
                    _state.value = HealthImprovementsState(
                        message = it.message,
                        isError = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}