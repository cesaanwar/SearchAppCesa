package com.cesa.searchappcesa.data.remote

import com.cesa.searchappcesa.search.Products
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface SearchService {

    @POST("./")
    @Headers("Content-Type: application/json")
    suspend fun getProducts(@Body requestParams: HashMap<String, String>): Products

}