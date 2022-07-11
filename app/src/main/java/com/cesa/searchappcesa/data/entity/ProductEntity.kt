package com.cesa.searchappcesa.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val productId: Long,
    val productName: String,
    val productPrice: String,
    val departmentId: Int,
    val ratingAverage: String,
    val productImage: String
)
