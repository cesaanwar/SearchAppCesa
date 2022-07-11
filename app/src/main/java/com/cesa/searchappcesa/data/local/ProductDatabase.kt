package com.cesa.searchappcesa.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cesa.searchappcesa.data.entity.ProductEntity

@Database(entities = [ProductEntity::class], version = 1, exportSchema = false)
abstract class ProductDatabase: RoomDatabase() {

    abstract fun getProductDao(): ProductDao

}