package com.cesa.searchappcesa.data.remote

import com.cesa.searchappcesa.data.SearchDataSource
import com.cesa.searchappcesa.search.Product
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteSearchDataSource @Inject constructor(
    val service: SearchService
): SearchDataSource {

    override suspend fun getProducts(query: String): List<Product> {
        val res = service.getProducts("android", "search", 10, query)
        return res.result.products
    }

    override suspend fun cacheProducts(products: List<Product>) {
    }


}