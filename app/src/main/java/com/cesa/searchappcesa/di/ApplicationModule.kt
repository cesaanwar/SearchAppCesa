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

@Module
abstract class ApplicationBinds {

    @Binds
    abstract fun bindRepository(repository: DefaultSearchRepository): SearchRepository

}