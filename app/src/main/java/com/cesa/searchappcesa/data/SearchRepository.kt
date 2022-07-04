package com.cesa.searchappcesa.data

import com.cesa.searchappcesa.search.Product

interface SearchRepository {

    suspend fun getProducts(query: String): Result<List<Product>>

}