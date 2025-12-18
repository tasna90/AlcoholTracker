package com.example.alcoholTracker.domain.use_case

import com.example.alcoholTracker.common.Resource
import com.example.alcoholTracker.data.Drink
import com.example.alcoholTracker.domain.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetDrinksUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(): Flow<Resource<List<Drink>>> = flow {
        try {
            emit(Resource.Loading<List<Drink>>())
            val drinks = repository.getDrinks()
            delay(3000)
            emit(Resource.Success<List<Drink>>(data = drinks))
        } catch (e: Exception) {
            emit(Resource.Failure<List<Drink>>(e.localizedMessage))
        }
    }.flowOn(Dispatchers.IO)
}