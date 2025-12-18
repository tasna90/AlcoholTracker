package com.example.alcoholTracker.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.ZonedDateTime

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    val uid: Int = 0,
    @ColumnInfo(name = "first_name")
    val firstName: String,
    @ColumnInfo(name = "last_name")
    val lastName: String,
    @ColumnInfo(name = "nick_name")
    val nickName: String,
    @ColumnInfo(name = "date_of_birth")
    val dateOfBirth: ZonedDateTime,
    @ColumnInfo(name = "date_of_alcohol_stop")
    val dateOfAlcoholStop: ZonedDateTime,
    @ColumnInfo(name = "money_spent_on_alcohol_weekly")
    val moneySpentOnAlcoholWeekly: Long,
    @ColumnInfo(name = "favorite_drink")
    val favoriteDrink: String,
    @ColumnInfo(name = "favorite_drink_type")
    val favoriteDrinkType: String,
    @ColumnInfo(name = "consumed_favorite_drinks_weekly")
    val consumedFavoriteDrinksWeekly: Long
)

// refactor to INSTANT