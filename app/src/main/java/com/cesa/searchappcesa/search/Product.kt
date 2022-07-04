package com.cesa.searchappcesa.search
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


data class Products(
    @SerializedName("config")
    val config: Config,
    @SerializedName("header")
    val header: Header,
    @SerializedName("result")
    val result: Result,
    @SerializedName("server_process_time")
    val serverProcessTime: Double,
    @SerializedName("status")
    val status: String
)

data class Config(
    @SerializedName("backoff_multi")
    val backoffMulti: Int,
    @SerializedName("max_retries")
    val maxRetries: Int,
    @SerializedName("timeout")
    val timeout: Int
)

data class Header(
    @SerializedName("additional_params")
    val additionalParams: String,
    @SerializedName("component_id")
    val componentId: String,
    @SerializedName("default_view")
    val defaultView: Int,
    @SerializedName("error_message")
    val errorMessage: String,
    @SerializedName("keyword_process")
    val keywordProcess: String,
    @SerializedName("meta")
    val meta: Meta,
    @SerializedName("process_time")
    val processTime: Int,
    @SerializedName("response_code")
    val responseCode: Int,
    @SerializedName("total_data")
    val totalData: Int,
    @SerializedName("total_data_text")
    val totalDataText: String
)

data class Result(
    @SerializedName("breadcrumb")
    val breadcrumb: Any,
    @SerializedName("department_id")
    val departmentId: Int,
    @SerializedName("has_catalog")
    val hasCatalog: Int,
    @SerializedName("hashtag")
    val hashtag: Any,
    @SerializedName("locations")
    val locations: Any,
    @SerializedName("paging")
    val paging: Paging,
    @SerializedName("products")
    val products: List<Product>,
    @SerializedName("search_url")
    val searchUrl: String,
    @SerializedName("share_url")
    val shareUrl: String,
    @SerializedName("st")
    val st: String
)

data class Meta(
    @SerializedName("product_list_type")
    val productListType: String
)

data class Paging(
    @SerializedName("uri_next")
    val uriNext: String,
    @SerializedName("uri_previous")
    val uriPrevious: String
)

@Entity(tableName = "products")
data class Product constructor(
    @PrimaryKey
    @SerializedName("product_id")
    val productId: Long,
    @SerializedName("product_image")
    val productImage: String,
    @SerializedName("product_name")
    val productName: String,
    @SerializedName("product_price")
    val productPrice: String,
    @SerializedName("rate")
    val rate: Int,
    @SerializedName("stock")
    val stock: Int
) {
    fun getFormattedRating(): String = String.format("%.1f", rate.toDouble() / 20)
}

data class Badge(
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("show")
    val show: Boolean,
    @SerializedName("title")
    val title: String
)

data class Label(
    @SerializedName("color")
    val color: String,
    @SerializedName("title")
    val title: String
)