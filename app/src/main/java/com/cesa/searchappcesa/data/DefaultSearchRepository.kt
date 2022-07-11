package com.cesa.searchappcesa.data

import com.cesa.searchappcesa.data.entity.ProductEntity
import com.cesa.searchappcesa.data.local.LocalSearchDataSource
import com.cesa.searchappcesa.data.remote.RemoteSearchDataSource
import com.cesa.searchappcesa.domain.response.ProductsResponse
import com.cesa.searchappcesa.domain.response.ShopsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultSearchRepository @Inject constructor(
    private val remoteSearchDataSource: RemoteSearchDataSource,
    private val localSearchDataSource: LocalSearchDataSource,
): SearchRepository {

    override suspend fun getProductsFromRemote(query: String, start: Int): ProductsResponse? =
    withContext(Dispatchers.IO) {
        remoteSearchDataSource.getProducts(query, start)
    }


    override suspend fun getProductFromLocal(query: String, start: Int): List<ProductEntity> = withContext(Dispatchers.IO) {
        localSearchDataSource.getProducts(query)
    }


    override suspend fun cacheProducts(products: List<ProductEntity>) {
        withContext(Dispatchers.IO) {
            localSearchDataSource.cacheProducts(products)
        }
    }

    override suspend fun getShopsFromRemote(query: String, start: Int): ShopsResponse? =
        withContext(Dispatchers.IO) {
            remoteSearchDataSource.getShops(query, start)
        }


}