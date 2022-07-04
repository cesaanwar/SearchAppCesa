package com.cesa.searchappcesa.search

import androidx.lifecycle.*
import com.cesa.searchappcesa.data.DefaultSearchRepository
import com.cesa.searchappcesa.data.Result
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val repository: DefaultSearchRepository
): ViewModel() {

    private val _query = MutableLiveData<String>()

    private val _errorEvent = MutableLiveData<Boolean>()
    val errorEvent = _errorEvent

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading = _isLoading

    private val _productsLiveData : LiveData<List<Product>> = _query.switchMap { query ->
        liveData {
            val res: Result<List<Product>> = repository.getProducts(query)
            val items = mutableListOf<Product>()
            if (res is Result.Success) {
                items.addAll(res.data)
            } else if (res is Result.Error<*>) {
                _errorEvent.value = true
                res.data?.let {
                    items.addAll(it as Collection<Product>)
                }
            }
            _isLoading.value = false
            emit(items.toList())
        }
    }

    val products = _productsLiveData

    fun enterQuery(query: String) {
        _isLoading.value = true
        _query.value = query
    }

}