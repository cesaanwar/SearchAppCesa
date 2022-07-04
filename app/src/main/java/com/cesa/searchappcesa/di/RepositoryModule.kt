package com.cesa.searchappcesa.di

import com.bumptech.glide.load.DataSource
import com.cesa.searchappcesa.data.SearchDataSource
import com.cesa.searchappcesa.data.remote.RemoteSearchDataSource
import dagger.Binds
import dagger.Module

abstract class RepositoryModule {

    abstract fun bindRemoteDataSource(dataSource: RemoteSearchDataSource): SearchDataSource

}