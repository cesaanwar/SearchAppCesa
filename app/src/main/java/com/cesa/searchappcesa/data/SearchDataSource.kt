package com.cesa.searchappcesa.data

import com.cesa.searchappcesa.search.Product

interface SearchDataSource {

    suspend fun getProducts(query: String): List<Product>

    suspend fun cacheProducts(products: List<Product>)

}