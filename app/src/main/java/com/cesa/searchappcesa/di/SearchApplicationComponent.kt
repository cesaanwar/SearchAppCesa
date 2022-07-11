package com.cesa.searchappcesa.di

import android.content.Context
import com.cesa.searchappcesa.SearchApplication
import com.cesa.searchappcesa.search.SearchActivity
import com.cesa.searchappcesa.search.products.ProductListFragment
import com.cesa.searchappcesa.search.shops.ShopListFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationBinds::class,
        DatabaseModule::class,
        NetworkModule::class,
        ViewModelModule::class
    ],
)
interface SearchApplicationComponent{

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): SearchApplicationComponent
    }

    fun inject(searchActivity: SearchActivity)

    fun inject(productListFragment: ProductListFragment)

    fun inject(shopListFragment: ShopListFragment)

}