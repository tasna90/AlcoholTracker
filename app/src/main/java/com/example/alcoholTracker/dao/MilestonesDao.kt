package com.example.alcoholTracker.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.alcoholTracker.data.Milestone

@Dao
interface MilestonesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMilestones(milestones: List<Milestone>)

    @Query("SELECT * FROM milestone")
    suspend fun getAllMilestones(): List<Milestone>

    @Query("SELECT * FROM milestone WHERE uId = :id")
    suspend fun getMilestoneById(id: Int): Milestone
}