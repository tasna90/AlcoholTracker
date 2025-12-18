package com.example.alcoholTracker.presentation.registration

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alcoholTracker.common.Resource
import com.example.alcoholTracker.data.User
import com.example.alcoholTracker.domain.use_case.CreateUserUseCase
import com.example.alcoholTracker.domain.use_case.GetDrinksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val createUserUseCase: CreateUserUseCase,
    private val getDrinksUseCase: GetDrinksUseCase
): ViewModel() {

    private val _drinksState = mutableStateOf(DrinksState(isLoading = true))
    val drinksState: State<DrinksState> = _drinksState

    init {
        getDrinks()
    }

    fun createUser(user: User) {
        createUserUseCase.invoke(user).launchIn(viewModelScope)
    }

    private fun getDrinks() {

        getDrinksUseCase.invoke().onEach {
            when(it) {
                is Resource.Success -> {
                    _drinksState.value = DrinksState(
                        drinks = it.data,
                        isSuccess = true
                    )
                }
                is Resource.Loading -> {
                    _drinksState.value = DrinksState(
                        isLoading = true
                    )
                }
                is Resource.Failure -> {
                    _drinksState.value = DrinksState(
                        message = it.message,
                        isError = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}