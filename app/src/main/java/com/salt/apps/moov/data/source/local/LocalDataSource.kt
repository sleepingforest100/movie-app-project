package com.salt.apps.moov.data.source.local

import com.salt.apps.moov.data.source.local.entity.MovieEntity
import com.salt.apps.moov.data.source.local.room.MovieDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    private val movieDao: MovieDao
) {
    suspend fun insertMovies(movies: List<MovieEntity>) = movieDao.insertMovies(movies)

    fun getMovies(type: String) = movieDao.getMovies(type)

    fun getFavoriteMovies() = movieDao.getFavoriteMovies()

    fun getMovieById(moovId: Int) = movieDao.getMovieById(moovId)

    suspend fun updateMovieById(movieId: Int, isFavorite: Boolean) =
        movieDao.updateMovieById(movieId, isFavorite)
}