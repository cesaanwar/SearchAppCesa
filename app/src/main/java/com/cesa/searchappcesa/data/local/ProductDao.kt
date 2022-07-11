package com.cesa.searchappcesa.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cesa.searchappcesa.data.entity.ProductEntity

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(products: List<ProductEntity>)

    @Query("select * from products p where p.productName like '%' || :query || '%'")
    suspend fun getProductBasedOnQuery(query: String): List<ProductEntity>

}