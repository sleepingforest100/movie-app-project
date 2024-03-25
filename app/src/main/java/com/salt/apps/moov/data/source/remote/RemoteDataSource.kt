package com.salt.apps.moov.data.source.remote

import com.salt.apps.moov.data.source.remote.network.MovieApiResponse
import com.salt.apps.moov.data.source.remote.network.MovieApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

// Класс RemoteDataSource предоставляет доступ к данным из удалённого API.
@Singleton // Аннотация Singleton гарантирует, что в пределах приложения будет создан только один экземпляр этого класса.
class RemoteDataSource @Inject constructor(
    private val movieApiService: MovieApiService // Внедрение зависимости сервиса API для доступа к данным о фильмах.
) {
    // Функция для получения списка популярных фильмов. Возвращает поток данных типа MovieApiResponse.
    suspend fun getPopularMovies() = flow {
        try {
            val response = movieApiService.getPopularMovies() // Запрос к API на получение популярных фильмов.
            val data = response.results // Извлечение списка фильмов из ответа.
            if (data.isNotEmpty()) emit(MovieApiResponse.Success(data)) // Если список не пустой, передаём его как успешный результат.
            else emit(MovieApiResponse.Empty) // Если список пустой, передаём специальный объект Empty.
        } catch (e: Exception) {
            emit(MovieApiResponse.Error(e.message.toString())) // В случае ошибки передаём объект Error с сообщением об ошибке.
        }
    }.flowOn(Dispatchers.IO) // Указываем, что поток должен выполняться в пуле фоновых потоков.

    // Функция для получения списка предстоящих фильмов. Логика работы аналогична функции getPopularMovies.
    suspend fun getUpcomingMovies() = flow {
        try {
            val response = movieApiService.getUpcomingMovies()
            val data = response.results
            if (data.isNotEmpty()) emit(MovieApiResponse.Success(data))
            else emit(MovieApiResponse.Empty)
        } catch (e: Exception) {
            emit(MovieApiResponse.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)
}
