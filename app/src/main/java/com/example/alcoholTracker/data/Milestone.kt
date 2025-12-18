package com.example.alcoholTracker.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Milestone(
    @PrimaryKey(autoGenerate = true)
    val uId: Int = 0,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "icon")
    val icon: String
)
