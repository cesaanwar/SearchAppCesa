package com.cesa.searchappcesa.data.local

import com.cesa.searchappcesa.data.SearchDataSource
import com.cesa.searchappcesa.data.entity.ProductEntity
import com.cesa.searchappcesa.domain.response.Product
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalSearchDataSource @Inject constructor(
    private val database: ProductDatabase
) {

    suspend fun getProducts(query: String): List<ProductEntity> {
        return database.getProductDao().getProductBasedOnQuery(query)
    }

    suspend fun cacheProducts(products: List<ProductEntity>) {
        database.getProductDao().insertTask(products)
    }
}