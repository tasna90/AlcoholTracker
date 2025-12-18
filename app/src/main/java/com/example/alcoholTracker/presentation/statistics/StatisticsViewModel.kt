package com.example.alcoholTracker.presentation.statistics

import android.annotation.SuppressLint
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alcoholTracker.common.Resource
import com.example.alcoholTracker.data.AvoidedDrinks
import com.example.alcoholTracker.data.SavedMoney
import com.example.alcoholTracker.domain.use_case.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class StatisticsViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase
): ViewModel() {
    private val _statisticsState = mutableStateOf(StatisticsState(isLoading = true))
    val statisticsState: State<StatisticsState> = _statisticsState

    init {
        getUser()
    }

    private fun getUser() {
        getUserUseCase.invoke().onEach {
            when(it) {
                is Resource.Failure -> {
                    _statisticsState.value = StatisticsState(
                        message = it.message,
                        isError = true
                    )
                }
                is Resource.Loading -> {
                    _statisticsState.value = StatisticsState(
                        isLoading = true
                    )
                }
                is Resource.Success -> {
                    _statisticsState.value = StatisticsState(
                        isSuccess = true,
                        avoidedDrinks = AvoidedDrinks(
                            daily = dailySkippedDrinks(
                                it.data?.consumedFavoriteDrinksWeekly
                            ),
                            weekly = weeklySkippedDrinks(
                                it.data?.consumedFavoriteDrinksWeekly
                            ),
                            monthly = monthlySkippedDrinks(
                                it.data?.consumedFavoriteDrinksWeekly
                            ),
                            yearly = yearlySkippedDrinks(
                                it.data?.consumedFavoriteDrinksWeekly
                            )
                        ),
                        savedMoney = SavedMoney(
                            daily = dailySavedMoney(
                                it.data?.moneySpentOnAlcoholWeekly
                            ),
                            weekly = weeklySavedMoney(
                                it.data?.moneySpentOnAlcoholWeekly
                            ),
                            monthly = monthlySavedMoney(
                                it.data?.moneySpentOnAlcoholWeekly
                            ),
                            yearly = yearlySavedMoney(
                                it.data?.moneySpentOnAlcoholWeekly
                            )
                        )
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    @SuppressLint("DefaultLocale")
    private fun dailySkippedDrinks(consumedFavoriteDrinksWeekly: Long?): String {
        return String.format(
            "%.0f", consumedFavoriteDrinksWeekly?.toDouble()?.div(7)
        )
    }

    @SuppressLint("DefaultLocale")
    private fun weeklySkippedDrinks(consumedFavoriteDrinksWeekly: Long?): String {
        return String.format(
            "%.0f", consumedFavoriteDrinksWeekly?.toDouble()
        )
    }

    @SuppressLint("DefaultLocale")
    private fun monthlySkippedDrinks(consumedFavoriteDrinksWeekly: Long?): String {
        return String.format(
            "%.0f", consumedFavoriteDrinksWeekly?.toDouble()?.times(4)
        )
    }

    @SuppressLint("DefaultLocale")
    private fun yearlySkippedDrinks(consumedFavoriteDrinksWeekly: Long?): String {
        return String.format(
            "%.0f", consumedFavoriteDrinksWeekly?.toDouble()?.times(4)?.times(12)
        )
    }

    @SuppressLint("DefaultLocale")
    private fun dailySavedMoney(moneySpentOnAlcoholWeekly: Long?): String {
        return String.format(
            "%.0f", moneySpentOnAlcoholWeekly?.toDouble()?.div(7)
        )
    }

    @SuppressLint("DefaultLocale")
    private fun weeklySavedMoney(moneySpentOnAlcoholWeekly: Long?): String {
        return String.format(
            "%.0f", moneySpentOnAlcoholWeekly?.toDouble()
        )
    }

    @SuppressLint("DefaultLocale")
    private fun monthlySavedMoney(moneySpentOnAlcoholWeekly: Long?): String {
        return String.format(
            "%.0f", moneySpentOnAlcoholWeekly?.toDouble()?.times(4)
        )
    }

    @SuppressLint("DefaultLocale")
    private fun yearlySavedMoney(moneySpentOnAlcoholWeekly: Long?): String {
        return String.format(
            "%.0f", moneySpentOnAlcoholWeekly?.toDouble()?.times(4)?.times(12)
        )
    }
}