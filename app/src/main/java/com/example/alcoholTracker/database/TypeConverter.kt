package com.example.alcoholTracker.database

import androidx.room.TypeConverter
import java.math.BigDecimal
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class TypeConverter {

    @TypeConverter
    fun timeStampToZonedDateTime(timeStamp: String): ZonedDateTime {
        val timeStampAsInstant = Instant.parse(timeStamp)
        return timeStampAsInstant.atZone(ZoneId.systemDefault())
    }

    @TypeConverter
    fun zonedDateTimeToTimeStamp(zonedDateTime: ZonedDateTime): String {
        return zonedDateTime.format(DateTimeFormatter.ISO_INSTANT)
    }

    @TypeConverter
    fun bigDecimalToString(bigDecimal: BigDecimal): String {
        return bigDecimal.toString()
    }

    @TypeConverter
    fun stringToBigDecimal(string: String): BigDecimal {
        return string.toBigDecimal()
    }
}