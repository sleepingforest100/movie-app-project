package com.salt.apps.moov.data.source.remote

import com.salt.apps.moov.data.source.remote.network.MovieApiResponse
import com.salt.apps.moov.data.source.remote.network.MovieApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val movieApiService: MovieApiService
) {
    suspend fun getPopularMovies() = flow {
        try {
            val response = movieApiService.getPopularMovies()
            val data = response.results
            if (data.isNotEmpty()) emit(MovieApiResponse.Success(data))
            else emit(MovieApiResponse.Empty)
        } catch (e: Exception) {
            emit(MovieApiResponse.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    suspend fun getUpcomingMovies() = flow {
        try {
            val response = movieApiService.getUpcomingMovies()
            val data = response.results
            if (data.isNotEmpty()) emit(MovieApiResponse.Success(data))
            else (emit(MovieApiResponse.Empty))
        } catch (e: Exception) {
            emit(MovieApiResponse.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)
}