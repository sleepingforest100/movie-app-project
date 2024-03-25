package com.salt.apps.moov.data.source.remote.response

import com.google.gson.annotations.SerializedName

// Класс MovieResponse представляет структуру ответа API при запросе информации о фильмах.
data class MovieResponse(
    @field:SerializedName("page") // Аннотация указывает, что JSON-поле "page" соответствует переменной page.
    val page: Int, // Номер страницы результатов.

    @field:SerializedName("results") // Указывает на соответствие JSON-массива "results" списку объектов MovieItem.
    val results: List<MovieItem> // Список фильмов, полученных в результате запроса.
)

// Класс MovieItem описывает структуру данных для каждого фильма в ответе.
data class MovieItem(
    @field:SerializedName("id") // Уникальный идентификатор фильма.
    val id: Int,

    @field:SerializedName("overview") // Краткое описание фильма.
    val overview: String? = null,

    @field:SerializedName("backdrop_path") // Путь к изображению фона.
    val backdropPath: String? = null,

    @field:SerializedName("original_language") // Оригинальный язык фильма.
    val originalLanguage: String,

    @field:SerializedName("release_date") // Дата выпуска фильма.
    val releaseDate: String? = null,

    @field:SerializedName("popularity") // Популярность фильма.
    val popularity: Double,

    @field:SerializedName("vote_average") // Средняя оценка фильма.
    val voteAverage: Double,

    @field:SerializedName("title") // Название фильма.
    val title: String,

    @field:SerializedName("genre_ids") // Список идентификаторов жанров, к которым относится фильм.
    val genreIds: List<Int?>,

    @field:SerializedName("vote_count") // Количество голосов за фильм.
    val voteCount: Int,

    @field:SerializedName("poster_path") // Путь к изображению постера фильма.
    val posterPath: String? = null
)
