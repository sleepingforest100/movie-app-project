package com.salt.apps.moov.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.salt.apps.moov.data.source.local.entity.MovieEntity
import com.salt.apps.moov.utilities.Constants.DB_VERSION

// Аннотация Database задает основные характеристики базы данных для Room.
@Database(
    entities = [MovieEntity::class], // Список классов, представляющих таблицы в базе данных.
    version = DB_VERSION, // Версия базы данных, где DB_VERSION - константа, задающая текущую версию.
    exportSchema = false // Отключает экспорт схемы базы данных в JSON файлы. Используется для упрощения разработки.
)
// Аннотация TypeConverters указывает Room использовать указанные конвертеры для преобразования данных.
@TypeConverters(Converters::class)
abstract class MovieDatabase : RoomDatabase() {
    // Функция, предоставляющая доступ к объекту MovieDao, через который осуществляется работа с данными.
    abstract fun movieDao(): MovieDao
}

// Класс Converters содержит методы для преобразования между типами данных, которые Room не может хранить напрямую, и строками.
class Converters {
    // Преобразует строку JSON в список целых чисел. Используется для хранения списков в виде JSON строки.
    @TypeConverter
    fun fromString(value: String?): List<Int>? {
        if (value == null) {
            return emptyList()
        }
        // Определение типа для дженерика List<Int> для последующего преобразования из JSON.
        val listType = object : TypeToken<List<Int>>() {}.type
        return Gson().fromJson(value, listType) // Преобразование строки JSON в список целых чисел.
    }

    // Преобразует список целых чисел в строку JSON. Используется при сохранении данных в базу.
    @TypeConverter
    fun toString(list: List<Int>?): String {
        return Gson().toJson(list) // Преобразование списка целых чисел в строку JSON.
    }
}
