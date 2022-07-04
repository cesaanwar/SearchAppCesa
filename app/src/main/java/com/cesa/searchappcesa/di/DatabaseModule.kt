package com.cesa.searchappcesa.di

import android.content.Context
import androidx.room.Room
import com.cesa.searchappcesa.data.local.ProductDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): ProductDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            ProductDatabase::class.java,
            "Product.db"
        ).build()
    }

}