package com.cesa.searchappcesa.search

import androidx.lifecycle.*
import com.cesa.searchappcesa.data.Result
import com.cesa.searchappcesa.domain.usecase.GetProductsUseCase
import com.cesa.searchappcesa.domain.usecase.GetShopsUseCase
import com.cesa.searchappcesa.search.products.ProductUIModel
import com.cesa.searchappcesa.search.shops.ShopUIModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val getShopsUseCase: GetShopsUseCase
): ViewModel() {

    private val _errorEvent = MutableLiveData<Boolean>()
    val errorEvent = _errorEvent

    private val _isProductRefreshing = MutableLiveData<Boolean>()
    val isProductRefreshing = _isProductRefreshing

    private val _isShopRefreshing = MutableLiveData<Boolean>()
    val isShopRefreshing = _isShopRefreshing

    private var currentQuery = "tokopedia"

    private var isProductLoadingMore = false
    private var productPageNo = 1

    private var isShopLoadingMore = false
    private var shopPageNo = 1

    private val _shopsLiveData: MutableLiveData<List<ShopUIModel>> = MutableLiveData()
    val shops: LiveData<List<ShopUIModel>> = _shopsLiveData.switchMap {
        liveData {
            emit(it)
        }
    }

    private val _productsLiveData: MutableLiveData<List<ProductUIModel>> = MutableLiveData()
    val products: LiveData<List<ProductUIModel>> = _productsLiveData.switchMap {
        liveData {
            emit(it)
        }
    }

    fun fetchData(query: String) {
        currentQuery = query
        productPageNo = 1
        shopPageNo = 1
        _isShopRefreshing.value = true
        _isProductRefreshing.value = true
        _shopsLiveData.value = mutableListOf()
        _productsLiveData.value = mutableListOf()
        CoroutineScope(Dispatchers.Main).launch {
            loadShops()
            loadProducts()
        }
    }

    private fun loadShops() {
        viewModelScope.launch {
            val res = async { getShopsUseCase.getShops(currentQuery, shopPageNo) }
            res.await().let { result ->
                val value = mutableListOf<ShopUIModel>()
                _shopsLiveData.value?.let {
                    value.addAll(it)
                }
                if (result is Result.Success) {
                    value.addAll(result.data)
                } else if (result is Result.Error) {
                    result.data?.let { value.addAll(it) }
                }
                _shopsLiveData.value = value
                isShopLoadingMore = false
                _isShopRefreshing.value = false
            }
        }
    }
    private fun loadProducts() {
        viewModelScope.launch {
            val res = async { getProductsUseCase.getProducts(currentQuery, productPageNo) }
            res.await().let { result ->
                val value = mutableListOf<ProductUIModel>()
                _productsLiveData.value?.let {
                    value.addAll(it)
                }
                if (result is Result.Success) {
                    value.addAll(result.data)
                } else if (result is Result.Error) {
                    result.data?.let { value.addAll(it) }
                }
                _productsLiveData.value = value
                isProductLoadingMore = false
                _isProductRefreshing.value = false
            }
        }
    }

    fun loadMoreProducts() {
        if (isProductLoadingMore || _isProductRefreshing.value == true) {
            return
        }
        productPageNo += 1
        isProductLoadingMore = true
        loadProducts()
    }

    fun loadMoreShops() {
        if (isProductLoadingMore || _isShopRefreshing.value == true) {
            return
        }
        shopPageNo += 1
        isShopLoadingMore = true
        loadShops()
    }

}