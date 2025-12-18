package com.example.alcoholTracker.domain.use_case

import com.example.alcoholTracker.common.Resource
import com.example.alcoholTracker.data.HealthImprovement
import com.example.alcoholTracker.domain.repository.Repository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetHealthImprovementsUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(): Flow<Resource<List<HealthImprovement>>> = flow {
        try {
            emit(Resource.Loading<List<HealthImprovement>>())
            val healthImprovements = repository.getHealthImprovements()
            delay(3000)
            emit(Resource.Success<List<HealthImprovement>>(data = healthImprovements))
        } catch (e: Exception) {
            emit(Resource.Failure<List<HealthImprovement>>(exception = e.localizedMessage))
        }
    }
}