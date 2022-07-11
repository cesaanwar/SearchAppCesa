package com.cesa.searchappcesa.domain.usecase

import com.cesa.searchappcesa.data.SearchRepository
import com.cesa.searchappcesa.domain.mapper.ProductMapper
import com.cesa.searchappcesa.search.products.ProductUIModel
import com.cesa.searchappcesa.data.Result
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val repository: SearchRepository,
    private val mapper: ProductMapper
) {

    suspend fun getProducts(query: String, start: Int): Result<List<ProductUIModel>> {
        try {
            val response = repository.getProductsFromRemote(query, start)
            response?.let {
                repository.cacheProducts(mapper.getProductsEntityFromResponse(it))
                return Result.Success(mapper.getProductsUIModelsFromResponse(it))
            }
            return Result.Error(mutableListOf<ProductUIModel>(), Exception("Unknown error, no result received"))
        } catch (e: Exception) {
            return Result.Error(mapper.getProductsUIModelFromEntity(repository.getProductFromLocal(query, start)), e)
        }
    }

}