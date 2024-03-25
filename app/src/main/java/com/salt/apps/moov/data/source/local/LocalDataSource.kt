package com.salt.apps.moov.data.source.local

import com.salt.apps.moov.data.source.local.entity.MovieEntity
import com.salt.apps.moov.data.source.local.room.MovieDao
import javax.inject.Inject
import javax.inject.Singleton

// Класс LocalDataSource предоставляет уровень абстракции над операциями с данными, выполняемыми через MovieDao.
@Singleton // Аннотация Singleton указывает, что будет создан только один экземпляр класса LocalDataSource.
class LocalDataSource @Inject constructor(
    private val movieDao: MovieDao // Внедрение зависимости MovieDao для доступа к операциям базы данных.
) {
    // Вставка списка фильмов в базу данных. Операция выполняется асинхронно.
    suspend fun insertMovies(movies: List<MovieEntity>) = movieDao.insertMovies(movies)

    // Получение списка фильмов определенного типа (например, популярных или предстоящих).
    fun getMovies(type: String) = movieDao.getMovies(type)

    // Получение списка избранных фильмов.
    fun getFavoriteMovies() = movieDao.getFavoriteMovies()

    // Получение фильма по его идентификатору.
    fun getMovieById(moovId: Int) = movieDao.getMovieById(moovId)

    // Обновление статуса избранного для конкретного фильма.
    suspend fun updateMovieById(movieId: Int, isFavorite: Boolean) =
        movieDao.updateMovieById(movieId, isFavorite)
}
