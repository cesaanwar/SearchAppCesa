package com.cesa.searchappcesa.domain.response

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class ProductsResponse(
    @SerializedName("data")
    val data: ProductData
)

data class ProductData(
    @SerializedName("searchProduct")
    val searchProduct: SearchProduct
)

data class SearchProduct(
    @SerializedName("products")
    val products: List<Product>
)

data class Product(
    @SerializedName("id")
    val productId: Long,
    @SerializedName("name")
    val productName: String,
    @SerializedName("price")
    val productPrice: String,
    @SerializedName("department_id")
    val departmentId: Int,
    @SerializedName("rating_average")
    val ratingAverage: String,
    @SerializedName("image_url")
    val productImage: String
)