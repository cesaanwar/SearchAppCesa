package com.cesa.searchappcesa.di

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule constructor(
    val context: Context
){

    @Provides
    fun provideContext(): Context = context

}