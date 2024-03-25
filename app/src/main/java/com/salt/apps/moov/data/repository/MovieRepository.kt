package com.salt.apps.moov.data.repository

import com.salt.apps.moov.data.Resource
import com.salt.apps.moov.data.model.Movie
import com.salt.apps.moov.data.model.MovieType
import com.salt.apps.moov.data.moovNetworkBoundResource
import com.salt.apps.moov.data.source.local.LocalDataSource
import com.salt.apps.moov.data.source.remote.RemoteDataSource
import com.salt.apps.moov.utilities.DataMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val remote: RemoteDataSource,
    private val local: LocalDataSource
) {
    fun getPopularMovies(): Flow<Resource<List<Movie>>> = moovNetworkBoundResource(
        query = {
            local.getMovies(MovieType.POPULAR.name).map {
                DataMapper.mapMovieEntitiesToMovieModel(it)
            }
        },
        fetch = {
            remote.getPopularMovies()
        },
        saveFetchResult = {
            val entity = DataMapper.mapMovieResponseToEntity(it, MovieType.POPULAR)
            local.insertMovies(entity)
        },
        shouldFetch = {
            it.isNullOrEmpty()
        }
    )

    fun getUpcomingMovies(): Flow<Resource<List<Movie>>> = moovNetworkBoundResource(
        query = {
            local.getMovies(MovieType.UPCOMING.name).map {
                DataMapper.mapMovieEntitiesToMovieModel(it)
            }
        },
        fetch = {
            remote.getUpcomingMovies()
        },
        saveFetchResult = {
            val entity = DataMapper.mapMovieResponseToEntity(it, MovieType.UPCOMING)
            local.insertMovies(entity)
        },
        shouldFetch = {
            it.isNullOrEmpty()
        }
    )

    fun getFavoriteMovies(): Flow<Resource<List<Movie>>> = flow {
        emit(Resource.Loading())
        try {
            val movieEntity = local.getFavoriteMovies().first()
            val movie = DataMapper.mapMovieEntitiesToMovieModel(movieEntity)
            emit(Resource.Success(movie))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message))
        }
    }

    fun getMovieById(moovId: Int): Flow<Resource<Movie>> = flow {
        emit(Resource.Loading())
        try {
            val movieEntity = local.getMovieById(moovId).first()
            val movie = DataMapper.mapMovieEntityToMovieModel(movieEntity)
            emit(Resource.Success(movie))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message))
        }
    }.flowOn(Dispatchers.IO)

    suspend fun updateMovieById(movieId: Int, isFavorite: Boolean) =
        local.updateMovieById(movieId, isFavorite)
}