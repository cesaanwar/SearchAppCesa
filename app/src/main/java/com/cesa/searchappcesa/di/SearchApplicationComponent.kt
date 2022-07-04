package com.cesa.searchappcesa.di

import android.content.Context
import com.cesa.searchappcesa.SearchApplication
import com.cesa.searchappcesa.search.SearchActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        DatabaseModule::class,
        NetworkModule::class
    ],
)
interface SearchApplicationComponent{

    fun inject(searchActivity: SearchActivity)

}