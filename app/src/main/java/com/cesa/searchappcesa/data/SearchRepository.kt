package com.cesa.searchappcesa.data

import com.cesa.searchappcesa.data.entity.ProductEntity
import com.cesa.searchappcesa.domain.response.ProductsResponse
import com.cesa.searchappcesa.domain.response.ShopsResponse

interface SearchRepository {

    suspend fun getProductsFromRemote(query: String, start: Int): ProductsResponse?

    suspend fun getProductFromLocal(query: String, start: Int): List<ProductEntity>

    suspend fun cacheProducts(products: List<ProductEntity>)

    suspend fun getShopsFromRemote(query: String, start: Int): ShopsResponse?

}