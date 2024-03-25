package com.salt.apps.moov.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.salt.apps.moov.data.source.local.entity.MovieEntity
import com.salt.apps.moov.utilities.Constants.DB_VERSION

@Database(
    entities = [MovieEntity::class],
    version = DB_VERSION,
    exportSchema = false
)

@TypeConverters(Converters::class)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}

class Converters {

    @TypeConverter
    fun fromString(value: String?): List<Int>? {
        if (value == null) {
            return emptyList()
        }
        val listType = object : TypeToken<List<Int>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toString(list: List<Int>?): String {
        return Gson().toJson(list)
    }
}