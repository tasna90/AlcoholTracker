package com.example.alcoholTracker.domain.use_case

import com.example.alcoholTracker.common.Resource
import com.example.alcoholTracker.data.Milestone
import com.example.alcoholTracker.domain.repository.Repository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMilestonesUseCase @Inject constructor(
    val repository: Repository
) {
    operator fun invoke(): Flow<Resource<List<Milestone>>> = flow {
        try {
            emit(Resource.Loading<List<Milestone>>())
            val milestones = repository.getMilestones()
            delay(3000)
            emit(Resource.Success<List<Milestone>>(data = milestones))
        } catch (e: Exception) {
            emit(Resource.Failure<List<Milestone>>(exception = e.localizedMessage))
        }
    }
}