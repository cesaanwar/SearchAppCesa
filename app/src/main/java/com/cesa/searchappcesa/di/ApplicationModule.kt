package com.cesa.searchappcesa.di

import android.content.Context
import com.cesa.searchappcesa.data.DefaultSearchRepository
import com.cesa.searchappcesa.data.SearchDataSource
import com.cesa.searchappcesa.data.SearchRepository
import com.cesa.searchappcesa.data.local.LocalSearchDataSource
import com.cesa.searchappcesa.data.local.ProductDatabase
import com.cesa.searchappcesa.data.remote.RemoteSearchDataSource
import com.cesa.searchappcesa.data.remote.SearchService
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Qualifier

@Module(includes = [ApplicationBinds::class])
class ApplicationModule constructor(
    private val context: Context
){

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class RemoteSearchDataSource

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class LocalSearchDataSource


    @RemoteSearchDataSource
    @Provides
    fun provideRemoteSearchDataSource(
        service: SearchService
    ): SearchDataSource {
        return RemoteSearchDataSource(service)
    }

    @LocalSearchDataSource
    @Provides
    fun provideLocalSearchDataSource(
        database: ProductDatabase
    ): SearchDataSource {
        return LocalSearchDataSource(database)
    }

    @Provides
    fun provideContext(): Context = context

}

@Module
abstract class ApplicationBinds {

    @Binds
    abstract fun bindRepository(repository: DefaultSearchRepository): SearchRepository

}