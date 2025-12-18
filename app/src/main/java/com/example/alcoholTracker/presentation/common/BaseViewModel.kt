package com.example.alcoholTracker.presentation.common

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alcoholTracker.domain.repository.getDarkMode
import com.example.alcoholTracker.domain.repository.setDarkMode
import com.example.alcoholTracker.domain.use_case.CreateHealthImprovementsUseCase
import com.example.alcoholTracker.domain.use_case.CreateMilestonesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BaseViewModel @Inject constructor(
    private val dataStore: DataStore<Preferences>,
    private val createHealthImprovementsUseCase: CreateHealthImprovementsUseCase,
    private val createMilestonesUseCase: CreateMilestonesUseCase
): ViewModel() {

    private val _isDarkThemeApplied = MutableStateFlow(false)
    val isDarkThemeApplied: StateFlow<Boolean> = _isDarkThemeApplied

    suspend fun createHealthImprovements(): Boolean {
        return createHealthImprovementsUseCase.invoke()
    }

    suspend fun createMilestones(): Boolean {
        return createMilestonesUseCase.invoke()
    }

    init {
        viewModelScope.launch {
            getDarkMode(dataStore).collect { isDarkMode ->
                _isDarkThemeApplied.value = isDarkMode
            }
        }
    }

    fun switchTheme() {
        viewModelScope.launch {
            setDarkMode(dataStore, !_isDarkThemeApplied.value)
        }
    }
}