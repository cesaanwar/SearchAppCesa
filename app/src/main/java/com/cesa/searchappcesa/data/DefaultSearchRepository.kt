package com.cesa.searchappcesa.data

import com.cesa.searchappcesa.di.ApplicationModule.RemoteSearchDataSource
import com.cesa.searchappcesa.di.ApplicationModule.LocalSearchDataSource
import com.cesa.searchappcesa.search.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultSearchRepository @Inject constructor(
    @RemoteSearchDataSource private val remoteSearchDataSource: SearchDataSource,
    @LocalSearchDataSource private val localSearchDataSource: SearchDataSource,
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