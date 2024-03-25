package com.salt.apps.moov.data

import com.salt.apps.moov.data.source.remote.network.MovieApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

// Функция moovNetworkBoundResource управляет процессом загрузки данных, определяя, когда следует
// получать данные из сети и когда использовать локальные данные.
inline fun <ResultType, RequestType> moovNetworkBoundResource(
    crossinline query: () -> Flow<ResultType>, // Локальный запрос к БД для получения данных.
    crossinline fetch: suspend () -> Flow<MovieApiResponse<RequestType>>, // Запрос к серверу для получения данных.
    crossinline saveFetchResult: suspend (RequestType) -> Unit, // Сохранение результатов запроса в локальную БД.
    crossinline shouldFetch: (ResultType?) -> Boolean = { true } // Условие для выполнения сетевого запроса.
) = flow {
    emit(Resource.Loading()) // Инициализация загрузки.
    val data = query().first() // Получение первичных данных из локального источника.
    if (shouldFetch(data)) { // Проверка условия для выполнения сетевого запроса.
        emit(Resource.Loading(data)) // Повторная инициализация загрузки с текущими данными.
        when (val response = fetch().first()) { // Получение данных с сервера.
            is MovieApiResponse.Success -> {
                saveFetchResult(response.data) // Сохранение полученных данных.
                emitAll(query().map { Resource.Success(it) }) // Возврат данных из локального хранилища.
            }
            is MovieApiResponse.Empty -> emitAll(query().map { Resource.Success(it) }) // Возврат пустого результата.
            is MovieApiResponse.Error -> emit(Resource.Error(response.errorMessage)) // Возврат ошибки.
        }
    } else {
        emitAll(query().map { Resource.Success(it) }) // Возврат данных из локального источника, если сетевой запрос не требуется.
    }
}
