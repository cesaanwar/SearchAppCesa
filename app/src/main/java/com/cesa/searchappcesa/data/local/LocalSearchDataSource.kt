package com.cesa.searchappcesa.data.local

import com.cesa.searchappcesa.data.SearchDataSource
import com.cesa.searchappcesa.search.Product
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalSearchDataSource @Inject constructor(
    private val database: ProductDatabase
) : SearchDataSource {

    override suspend fun getProducts(query: String): List<Product> {
        return database.getProductDao().getProductBasedOnQuery(query)
    }

    override suspend fun cacheProducts(products: List<Product>) {
        database.getProductDao().insertTask(products)
    }
}