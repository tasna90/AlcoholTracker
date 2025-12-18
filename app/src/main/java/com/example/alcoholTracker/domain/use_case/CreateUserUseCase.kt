package com.example.alcoholTracker.domain.use_case

import com.example.alcoholTracker.data.User
import com.example.alcoholTracker.domain.repository.Repository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CreateUserUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(user: User) = flow<Boolean> {
        try {
            repository.insertUser(user)
            emit(true)
        } catch (e: Exception) {
            emit(false)
        }
    }
}