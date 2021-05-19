package com.smendon.android.newshub.data

import kotlinx.coroutines.flow.*

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline queryDb: () -> Flow<ResultType>,
    crossinline fetchFromRemote: suspend () -> RequestType,
    crossinline cacheRemoteResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean = { true }
) = flow {
    val data = queryDb().first()
    val flow = if (shouldFetch(data)) {
        emit(Resource.Loading(data))
        try {
            cacheRemoteResult(fetchFromRemote())
            queryDb().map {
                Resource.Success(it)
            }
        } catch (throwable: Throwable) {
            queryDb().map {
                Resource.Error(throwable, it)
            }
        }
    } else {
        queryDb().map {
            Resource.Success(it)
        }
    }

    emitAll(flow)
}