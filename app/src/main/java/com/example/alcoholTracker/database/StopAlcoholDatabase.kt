package com.example.alcoholTracker.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.alcoholTracker.dao.HealthImprovementsDao
import com.example.alcoholTracker.dao.MilestonesDao
import com.example.alcoholTracker.data.User
import com.example.alcoholTracker.dao.UserDao
import com.example.alcoholTracker.data.HealthImprovement
import com.example.alcoholTracker.data.Milestone

@Database(
    entities = [User::class, HealthImprovement::class, Milestone::class],
    version = 1
)
@TypeConverters(TypeConverter::class)
abstract class AlcoholTrackerDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun healthImprovementsDao(): HealthImprovementsDao

    abstract fun milestonesDao(): MilestonesDao

}