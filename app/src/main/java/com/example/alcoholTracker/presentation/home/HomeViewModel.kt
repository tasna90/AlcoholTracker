package com.example.alcoholTracker.presentation.home

import android.annotation.SuppressLint
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alcoholTracker.common.Resource
import com.example.alcoholTracker.domain.use_case.GetHealthImprovementsUseCase
import com.example.alcoholTracker.domain.use_case.GetMilestonesUseCase
import com.example.alcoholTracker.domain.use_case.GetUserUseCase
import com.example.alcoholTracker.presentation.common.HealthImprovementsState
import com.example.alcoholTracker.presentation.common.MilestonesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.time.Duration
import java.time.ZonedDateTime
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val getHealthImprovementsUseCase: GetHealthImprovementsUseCase,
    private val getMilestonesUseCase: GetMilestonesUseCase
): ViewModel() {

    private val _state = mutableStateOf(HomeState(isLoading = true))
    val state: State<HomeState> = _state

    private val _passedTimeState = mutableStateOf(PassedTimeState())
    val passedTimeState: State<PassedTimeState> = _passedTimeState

    private val _moneyGainedState = mutableStateOf(MoneyGainedState())
    val moneyGainedState: State<MoneyGainedState> = _moneyGainedState

    private val _skippedDrinksState = mutableStateOf(SkippedDrinksState())
    val skippedDrinksState: State<SkippedDrinksState> = _skippedDrinksState

    private val _healthImprovementsState = mutableStateOf(HealthImprovementsState(isLoading = true))
    val healthImprovementsState: State<HealthImprovementsState> = _healthImprovementsState

    private val _milestonesState = mutableStateOf(MilestonesState(isLoading = true))
    val milestonesState: State<MilestonesState> = _milestonesState

    init {
        getUser()
        getAllHealthImprovements()
        getAllMilestones()
    }

    private fun getUser() {
        getUserUseCase.invoke().onEach {
            when (it) {
                is Resource.Success -> {
                    _state.value = HomeState(
                        user = it.data,
                        isSuccess = true
                    )

                    getPassedTime(_state.value.user?.dateOfAlcoholStop)
                    getNumberOfSkippedDrinks(_state.value.user?.consumedFavoriteDrinksWeekly)
                    getGainedMoney(_state.value.user?.moneySpentOnAlcoholWeekly)

                }

                is Resource.Loading -> {
                    _state.value = HomeState(isLoading = true)
                }

                is Resource.Failure -> {
                    _state.value = HomeState(
                        isError = true,
                        message = it.message
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getAllHealthImprovements() {
        getHealthImprovementsUseCase.invoke().onEach {
            when(it) {
                is Resource.Success -> {
                    _healthImprovementsState.value = HealthImprovementsState(
                        healthImprovements = it.data,
                        isSuccess = true
                    )
                }
                is Resource.Loading -> {
                    _healthImprovementsState.value = HealthImprovementsState(
                        isLoading = true
                    )
                }
                is Resource.Failure -> {
                    _healthImprovementsState.value = HealthImprovementsState(
                        message = it.message,
                        isError = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getAllMilestones() {
        getMilestonesUseCase.invoke().onEach {
            when (it) {
                is Resource.Success -> {
                    _milestonesState.value = MilestonesState(
                        milestones = it.data,
                        isSuccess = true
                    )
                }

                is Resource.Loading -> {
                    _milestonesState.value = MilestonesState(
                        isLoading = true
                    )
                }

                is Resource.Failure -> {
                    _milestonesState.value = MilestonesState(
                        message = it.message,
                        isError = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getPassedTime(dateOfAlcoholStop: ZonedDateTime?) {
        dateOfAlcoholStop.let {
            viewModelScope.launch {
                while (true) {
                    _passedTimeState.value = PassedTimeState(
                        days = getDaysPassedSinceAlcoholStop(),
                        hours = getHoursPassedSinceAlcoholStop(),
                        minutes = getMinutesPassedSinceAlcoholStop(),
                        seconds = getSecondsPassedSinceAlcoholStop()
                    )
                    delay(1000)
                }
            }
        }
    }

    private fun getNumberOfSkippedDrinks(consumedFavoriteDrinksWeekly: Long?) {
        consumedFavoriteDrinksWeekly.let {
            viewModelScope.launch {
                while (true) {
                    _skippedDrinksState.value = SkippedDrinksState(
                        numberOfSkippedDrinks = skippedDrinksSinceAlcoholStop(consumedFavoriteDrinksWeekly)
                    )
                    delay(60000)
                }
            }
        }
    }

    private fun getGainedMoney(moneySpentOnAlcoholWeekly: Long?) {
        moneySpentOnAlcoholWeekly.let {
            viewModelScope.launch {
                while (true) {
                    _moneyGainedState.value = MoneyGainedState(
                        moneyGained = moneyGainedByNotDrinking(moneySpentOnAlcoholWeekly)
                    )
                    delay(60000)
                }
            }
        }
    }

    private fun getDaysEarnedBack() {

    }

    private fun getDaysPassedSinceAlcoholStop(): Long {
        _state.value.user?.dateOfAlcoholStop.let {
            return Duration.between(_state.value.user?.dateOfAlcoholStop, ZonedDateTime.now()).toDays()
        }
    }

    private fun getHoursPassedSinceAlcoholStop(): Long {
        return Duration.between(_state.value.user?.dateOfAlcoholStop, ZonedDateTime.parse(ZonedDateTime.now().minusDays(getDaysPassedSinceAlcoholStop()).toString())).toHours()
    }

    private fun getMinutesPassedSinceAlcoholStop(): Long {
        return Duration.between(_state.value.user?.dateOfAlcoholStop, ZonedDateTime.parse(ZonedDateTime.now().minusDays(getDaysPassedSinceAlcoholStop()).minusHours(getHoursPassedSinceAlcoholStop()).toString())).toMinutes()
    }

    private fun getSecondsPassedSinceAlcoholStop(): Long {
        return Duration.between(_state.value.user?.dateOfAlcoholStop, ZonedDateTime.parse(ZonedDateTime.now().minusDays(getDaysPassedSinceAlcoholStop()).minusHours(getHoursPassedSinceAlcoholStop()).minusMinutes(getMinutesPassedSinceAlcoholStop()).toString())).seconds
    }

    @SuppressLint("DefaultLocale")
    private fun moneyGainedByNotDrinking(moneySpentOnAlcoholWeekly: Long?): String {
        return String.format(
            "%.0f", moneySpentOnAlcoholWeekly?.toDouble()?.div(7)?.times(getDaysPassedSinceAlcoholStop())?.plus(
                moneySpentOnAlcoholWeekly.toDouble().div(168).times(getHoursPassedSinceAlcoholStop()).plus(
                    moneySpentOnAlcoholWeekly.toDouble().div(10080).times(getMinutesPassedSinceAlcoholStop())
                )
            )
        )
    }

    @SuppressLint("DefaultLocale")
    private fun skippedDrinksSinceAlcoholStop(consumedFavoriteDrinksWeekly: Long?): String {
        return String.format(
            "%.0f", consumedFavoriteDrinksWeekly?.toDouble()?.div(7)?.times(getDaysPassedSinceAlcoholStop())?.plus(
                consumedFavoriteDrinksWeekly.toDouble().div(168).times(getHoursPassedSinceAlcoholStop()).plus(
                    consumedFavoriteDrinksWeekly.toDouble().div(10080).times(getMinutesPassedSinceAlcoholStop())
                )
            )
        )
    }
}