package com.cesa.searchappcesa.domain.response
import com.google.gson.annotations.SerializedName


data class ShopsResponse(
    @SerializedName("data")
    val `data`: ShopData
)

data class ShopData(
    @SerializedName("aceSearchShop")
    val aceSearchShop: AceSearchShop
)

data class AceSearchShop(
    @SerializedName("shops")
    val shops: List<Shop>
)

data class Shop(
    @SerializedName("products")
    val products: List<ShopProduct>,
    @SerializedName("shop_id")
    val shopId: Int,
    @SerializedName("shop_image")
    val shopImage: String,
    @SerializedName("shop_location")
    val shopLocation: String,
    @SerializedName("shop_name")
    val shopName: String
)

data class ShopProduct(
    @SerializedName("id")
    val id: Long,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("price_format")
    val priceFormat: String
)