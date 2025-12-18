package com.example.alcoholTracker.domain.use_case

import com.example.alcoholTracker.common.Resource
import com.example.alcoholTracker.data.User
import com.example.alcoholTracker.domain.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(): Flow<Resource<User>> = flow {
            try {
                emit(Resource.Loading<User>())
                val user = repository.getUser()
                delay(3000)
                emit(Resource.Success<User>(data = user))
            } catch (e: Exception) {
                emit(Resource.Failure<User>(exception = e.localizedMessage))
            }
    }.flowOn(Dispatchers.IO)
}