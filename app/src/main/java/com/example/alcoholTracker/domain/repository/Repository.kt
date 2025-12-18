package com.example.alcoholTracker.domain.repository

import com.example.alcoholTracker.data.Drink
import com.example.alcoholTracker.data.HealthImprovement
import com.example.alcoholTracker.data.Milestone
import com.example.alcoholTracker.data.User

interface Repository {
    suspend fun getUser(): User
    suspend fun insertUser(user: User)
    suspend fun getDrinks(): List<Drink>
    suspend fun getHealthImprovements(): List<HealthImprovement>
    suspend fun getHealthImprovementById(id: Int): HealthImprovement
    suspend fun insertAllHealthImprovements()

    suspend fun getMilestones(): List<Milestone>

    suspend fun getMilestoneById(id: Int): Milestone

    suspend fun insertAllMilestones()
}