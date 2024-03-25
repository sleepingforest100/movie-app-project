package com.salt.apps.moov.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

// Определение сущности для таблицы "movie" в базе данных приложения.
@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey // Обозначает поле как первичный ключ таблицы.
    var id: Int, // Уникальный идентификатор фильма.
    val overview: String? = null, // Краткое содержание фильма. Может быть null, если информация отсутствует.
    var backdropPath: String? = null, // Путь к изображению фона. Может быть null.
    var posterPath: String? = null, // Путь к постеру фильма. Может быть null.
    var originalLanguage: String, // Оригинальный язык фильма.
    var releaseDate: String? = null, // Дата выпуска фильма. Может быть null.
    var voteCount: Int? = null, // Количество голосов за фильм. Может быть null.
    var voteAverage: Double? = null, // Средний рейтинг фильма. Может быть null.
    var genreIds: List<Int?>, // Список идентификаторов жанров фильма. Может содержать null элементы.
    var movieType: String? = null, // Тип фильма (например, "Популярный", "Предстоящий"). Может быть null.
    var title: String, // Название фильма.
    var isFavorite: Boolean = false // Флаг, указывающий, добавлен ли фильм в избранное. По умолчанию false.
)
