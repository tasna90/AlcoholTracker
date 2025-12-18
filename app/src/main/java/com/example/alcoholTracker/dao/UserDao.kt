package com.example.alcoholTracker.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.alcoholTracker.data.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user LIMIT 1")
    suspend fun getUser(): User

    @Insert
    suspend fun insertUser(user: User)

}