package com.cesa.searchappcesa.data

import com.cesa.searchappcesa.data.local.LocalSearchDataSource
import com.cesa.searchappcesa.data.remote.RemoteSearchDataSource
import com.cesa.searchappcesa.search.Product
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class DefaultSearchRepository @Inject constructor(
    private val remoteSearchDataSource: RemoteSearchDataSource,
    private val localSearchDataSource: LocalSearchDataSource,
): SearchRepository {

    override suspend fun getProducts(query: String): Result<List<Product>> {
        return withContext(Dispatchers.IO) {
            try {
                val res = remoteSearchDataSource.getProducts(query)
                localSearchDataSource.cacheProducts(res)
                Result.Success(res)
            } catch (e: Exception) {
                val res = localSearchDataSource.getProducts(query)
                Result.Error(res, e)
            }
        }
    }

}