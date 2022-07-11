package com.cesa.searchappcesa.data.remote

import com.cesa.searchappcesa.domain.response.ProductsResponse
import com.google.gson.JsonObject
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface SearchService {

    @POST("./")
    @Headers("Content-Type: application/json")
    suspend fun request(@Body requestParams: RequestBody): Response<JsonObject>

}