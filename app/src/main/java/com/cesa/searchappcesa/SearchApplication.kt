package com.cesa.searchappcesa

import android.app.Application
import com.cesa.searchappcesa.di.DaggerSearchApplicationComponent
import com.cesa.searchappcesa.di.SearchApplicationComponent
import dagger.Component

class SearchApplication: Application() {

    val appComponent: SearchApplicationComponent = DaggerSearchApplicationComponent
        .factory().create(this)

}