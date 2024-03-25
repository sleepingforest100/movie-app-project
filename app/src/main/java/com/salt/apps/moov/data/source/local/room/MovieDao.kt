package com.salt.apps.moov.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.salt.apps.moov.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao // Аннотация, указывающая на Data Access Object (Объект доступа к данным) для Room.
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    // Функция для вставки списка фильмов в базу данных.
    // В случае конфликта (например, существующего id) данные будут заменены.
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Query("SELECT * FROM movie WHERE movieType = :movieType")
    // Запрашивает и возвращает поток всех фильмов заданного типа.
    fun getMovies(movieType: String): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie WHERE id=:movieId ORDER BY id")
    // Запрашивает и возвращает поток данных о фильме по его уникальному идентификатору.
    fun getMovieById(movieId: Int): Flow<MovieEntity>

    @Query("UPDATE movie SET isFavorite = :isFavorite WHERE id = :movieId")
    // Обновляет статус избранного у фильма с заданным id.
    suspend fun updateMovieById(movieId: Int, isFavorite: Boolean)

    @Query("SELECT * FROM movie WHERE isFavorite = 1")
    // Запрашивает и возвращает поток всех фильмов, добавленных в избранное.
    fun getFavoriteMovies(): Flow<List<MovieEntity>>
}
