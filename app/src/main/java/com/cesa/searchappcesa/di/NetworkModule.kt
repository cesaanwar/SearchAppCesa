package com.cesa.searchappcesa.di

import com.cesa.searchappcesa.data.remote.SearchService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideNetwork(): SearchService {
        return Retrofit.Builder()
            .baseUrl("https://ace.tokopedia.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SearchService::class.java)
    }

}