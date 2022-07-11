package com.cesa.searchappcesa.search.products

data class ProductUIModel (
    val productId: Long,
    val productImage: String,
    val productName: String,
    val productPrice: String,
    val formattedRating: String
)