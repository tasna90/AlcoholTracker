package com.example.alcoholTracker.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.alcoholTracker.data.HealthImprovement

@Dao
interface HealthImprovementsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllHealthImprovements(healthImprovements: List<HealthImprovement>)

    @Query("SELECT * FROM healthImprovement")
    suspend fun getAllHealthImprovements(): List<HealthImprovement>

    @Query("SELECT * FROM healthImprovement WHERE uId = :id")
    suspend fun getHealthImprovementById(id: Int): HealthImprovement

}