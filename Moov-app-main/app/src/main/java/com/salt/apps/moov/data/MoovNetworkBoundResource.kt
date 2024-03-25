package com.salt.apps.moov.data

import com.salt.apps.moov.data.source.remote.network.MovieApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

inline fun <ResultType, RequestType> moovNetworkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> Flow<MovieApiResponse<RequestType>>,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType?) -> Boolean = { true }
) = flow {
    emit(Resource.Loading())
    val data = query().first()
    if (shouldFetch(data)) {
        emit(Resource.Loading(data))
        when (val response = fetch().first()) {
            is MovieApiResponse.Success -> {
                saveFetchResult(response.data)
                emitAll(query().map { Resource.Success(it) })
            }

            is MovieApiResponse.Empty -> emitAll(query().map { Resource.Success(it) })
            is MovieApiResponse.Error -> emit(Resource.Error(response.errorMessage))
        }
    } else {
        emitAll(query().map { Resource.Success(it) })
    }
}