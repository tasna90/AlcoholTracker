package com.example.alcoholTracker.presentation.milestones

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alcoholTracker.common.Resource
import com.example.alcoholTracker.domain.use_case.GetMilestonesUseCase
import com.example.alcoholTracker.presentation.common.MilestonesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MilestonesViewModel @Inject constructor(
    private val getMilestonesUseCase: GetMilestonesUseCase
): ViewModel() {

    private val _state = mutableStateOf(MilestonesState(isLoading = true))
    val state: State<MilestonesState> = _state

    init {
        getAllMilestones()
    }

    private fun getAllMilestones() {
        getMilestonesUseCase.invoke().onEach {
            when (it) {
                is Resource.Success -> {
                    _state.value = MilestonesState(
                        milestones = it.data,
                        isSuccess = true
                    )
                }

                is Resource.Loading -> {
                    _state.value = MilestonesState(
                        isLoading = true
                    )
                }

                is Resource.Failure -> {
                    _state.value = MilestonesState(
                        message = it.message,
                        isError = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}