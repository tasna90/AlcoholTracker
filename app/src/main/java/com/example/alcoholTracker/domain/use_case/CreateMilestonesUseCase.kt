package com.example.alcoholTracker.domain.use_case

import com.example.alcoholTracker.domain.repository.Repository
import javax.inject.Inject

class CreateMilestonesUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend operator fun invoke(): Boolean {
        try {
            repository.insertAllMilestones()
            return true
        } catch (e: Exception) {
            return false
        }
    }
}