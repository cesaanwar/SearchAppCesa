package com.cesa.searchappcesa.search
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Products(
    @SerializedName("data")
    val data: Data
)

data class Data(
    @SerializedName("searchProduct")
    val searchProduct: SearchProduct
)

data class SearchProduct(
    @SerializedName("products")
    val products: List<Product>
)

@Entity(tableName = "products")
data class Product(
    @PrimaryKey
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
) {
   fun getFormattedRating(): String {
       return ratingAverage
   }
}