package com.salt.apps.moov.data.source.remote.network

import com.salt.apps.moov.data.source.remote.response.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

// Интерфейс, определяющий методы для обращения к API кинопортала для получения информации о фильмах.
interface MovieApiService {
    // Метод для получения списка популярных фильмов. Принимает параметры языка и номера страницы.
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("language") language: String = "en-US", // Параметр запроса для указания языка ответа. По умолчанию "en-US".
        @Query("page") page: Int = 1 // Параметр запроса для пагинации. По умолчанию загружается первая страница.
    ): MovieResponse // Возвращает ответ API в виде объекта MovieResponse.

    // Метод для получения списка предстоящих фильмов. Также принимает параметры языка и номера страницы.
    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("language") language: String = "en-US", // Параметр запроса для указания языка ответа, аналогично методу выше.
        @Query("page") page: Int = 1 // Параметр запроса для пагинации, аналогично методу выше.
    ): MovieResponse // Возвращает ответ API в виде объекта MovieResponse.
}
