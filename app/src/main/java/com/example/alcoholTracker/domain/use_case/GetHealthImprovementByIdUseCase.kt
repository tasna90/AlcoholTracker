package com.example.alcoholTracker.domain.use_case

import com.example.alcoholTracker.common.Resource
import com.example.alcoholTracker.data.HealthImprovement
import com.example.alcoholTracker.domain.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetHealthImprovementByIdUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(id: Int): Flow<Resource<HealthImprovement>> = flow {
        try {
            emit(Resource.Loading<HealthImprovement>())
            val healthImprovement = repository.getHealthImprovementById(id)
            delay(3000)
            emit(Resource.Success<HealthImprovement>(data = healthImprovement))
        } catch (e: Exception) {
            emit(Resource.Failure<HealthImprovement>(exception = e.localizedMessage))
        }
    }.flowOn(Dispatchers.IO)
}