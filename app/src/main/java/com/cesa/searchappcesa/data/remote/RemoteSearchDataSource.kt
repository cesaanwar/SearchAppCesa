package com.cesa.searchappcesa.data.remote

import com.cesa.searchappcesa.domain.response.ProductsResponse
import com.cesa.searchappcesa.domain.request.GetProductsRequest
import com.cesa.searchappcesa.domain.request.GetShopsRequest
import com.cesa.searchappcesa.domain.response.ProductData
import com.cesa.searchappcesa.domain.response.ShopData
import com.cesa.searchappcesa.domain.response.ShopsResponse
import com.google.gson.Gson
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteSearchDataSource @Inject constructor(
    private val service: SearchService
) {

    suspend fun getProducts(querySearch: String, start: Int): ProductsResponse {
        val request = GetProductsRequest(querySearch, start)
        val req = request.createRequestBody()
        val res = service.request(req)
        val resJson = res.body()
        return ProductsResponse(Gson().fromJson(resJson?.get("data"), ProductData::class.java))
    }

    suspend fun getShops(querySearch: String, start: Int): ShopsResponse {
        val request = GetShopsRequest(querySearch, start)
        val body = request.createRequestBody()
        val res = service.request(body)
        val resJson = res.body()
        return ShopsResponse(Gson().fromJson(resJson?.get("data"), ShopData::class.java))
    }

}