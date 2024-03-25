package com.salt.apps.moov.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.salt.apps.moov.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Query("SELECT * FROM moov WHERE movieType = :movieType")
    fun getMovies(movieType: String): Flow<List<MovieEntity>>

    @Query("SELECT * FROM moov WHERE id=:movieId ORDER BY id")
    fun getMovieById(movieId: Int): Flow<MovieEntity>

    @Query("UPDATE moov SET isFavorite = :isFavorite WHERE id = :movieId")
    suspend fun updateMovieById(movieId: Int, isFavorite: Boolean)

    @Query("SELECT * FROM moov WHERE isFavorite = 1")
    fun getFavoriteMovies(): Flow<List<MovieEntity>>
}