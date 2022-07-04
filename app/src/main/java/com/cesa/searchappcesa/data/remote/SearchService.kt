package com.cesa.searchappcesa.data.remote

import com.cesa.searchappcesa.search.Products
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {

    @GET("search/v1/product")
    suspend fun getProducts(@Query("device") device: String,
                    @Query("source") source: String,
                    @Query("rows") rows: Int,
                    @Query("q") q: String): Products

}