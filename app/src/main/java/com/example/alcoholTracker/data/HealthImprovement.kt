package com.example.alcoholTracker.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HealthImprovement(
    @PrimaryKey(autoGenerate = true)
    val uId: Int = 0,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "happens_after_given_days")
    val happensAfterGivenDays: Int
)
