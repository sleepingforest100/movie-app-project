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

// Класс MovieRepository управляет данными фильмов, обеспечивая их загрузку из различных источников.
class MovieRepository @Inject constructor(
    private val remote: RemoteDataSource, // Источник данных удаленного сервера.
    private val local: LocalDataSource // Источник локальных данных, например, база данных.
) {
    // Получение списка популярных фильмов.
    fun getPopularMovies(): Flow<Resource<List<Movie>>> = moovNetworkBoundResource(
        query = {
            // Запрос локальных данных популярных фильмов.
            local.getMovies(MovieType.POPULAR.name).map {
                DataMapper.mapMovieEntitiesToMovieModel(it) // Преобразование сущностей в модели фильмов.
            }
        },
        fetch = {
            remote.getPopularMovies() // Загрузка популярных фильмов с удаленного сервера.
        },
        saveFetchResult = {
            // Сохранение результатов загрузки в локальное хранилище.
            val entity = DataMapper.mapMovieResponseToEntity(it, MovieType.POPULAR)
            local.insertMovies(entity)
        },
        shouldFetch = {
            it.isNullOrEmpty() // Условие для загрузки данных: если локальный список пуст.
        }
    )

    // Получение списка предстоящих фильмов.
    fun getUpcomingMovies(): Flow<Resource<List<Movie>>> = moovNetworkBoundResource(
        query = {
            // Запрос локальных данных предстоящих фильмов.
            local.getMovies(MovieType.UPCOMING.name).map {
                DataMapper.mapMovieEntitiesToMovieModel(it) // Преобразование сущностей в модели фильмов.
            }
        },
        fetch = {
            remote.getUpcomingMovies() // Загрузка предстоящих фильмов с удаленного сервера.
        },
        saveFetchResult = {
            // Сохранение результатов загрузки в локальное хранилище.
            val entity = DataMapper.mapMovieResponseToEntity(it, MovieType.UPCOMING)
            local.insertMovies(entity)
        },
        shouldFetch = {
            it.isNullOrEmpty() // Условие для загрузки данных: если локальный список пуст.
        }
    )

    // Получение списка избранных фильмов.
    fun getFavoriteMovies(): Flow<Resource<List<Movie>>> = flow {
        emit(Resource.Loading()) // Инициирование состояния загрузки.
        try {
            val movieEntity = local.getFavoriteMovies().first()
            val movie = DataMapper.mapMovieEntitiesToMovieModel(movieEntity) // Преобразование сущностей в модели.
            emit(Resource.Success(movie)) // Возврат успешно загруженных данных.
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message)) // Возврат ошибки при возникновении исключения.
        }
    }

    // Получение фильма по идентификатору.
    fun getMovieById(moovId: Int): Flow<Resource<Movie>> = flow {
        emit(Resource.Loading()) // Инициирование состояния загрузки.
        try {
            val movieEntity = local.getMovieById(moovId).first()
            val movie = DataMapper.mapMovieEntityToMovieModel(movieEntity) // Преобразование сущности в модель.
            emit(Resource.Success(movie)) // Возврат успешно загруженных данных.
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message)) // Возврат ошибки при возникновении исключения.
        }
    }.flowOn(Dispatchers.IO) // Указание диспетчера для асинхронной работы.

    // Обновление статуса избранного фильма по идентификатору.
    suspend fun updateMovieById(movieId: Int, isFavorite: Boolean) =
        local.updateMovieById(movieId, isFavorite) // Обновление локального хранилища.
}
