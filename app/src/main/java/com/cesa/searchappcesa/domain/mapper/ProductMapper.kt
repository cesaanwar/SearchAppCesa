package com.cesa.searchappcesa.domain.mapper

import com.cesa.searchappcesa.data.entity.ProductEntity
import com.cesa.searchappcesa.domain.response.ProductsResponse
import com.cesa.searchappcesa.search.products.ProductUIModel
import javax.inject.Inject

class ProductMapper @Inject constructor(){

    fun getProductsUIModelsFromResponse(productsResponse: ProductsResponse): List<ProductUIModel> {
        val res = mutableListOf<ProductUIModel>()
        productsResponse.data.searchProduct.products.forEach {
            res.add(
                ProductUIModel(
                    it.productId,
                    it.productImage,
                    it.productName,
                    it.productPrice,
                    it.ratingAverage
                )
            )
        }
        return res
    }

    fun getProductsUIModelFromEntity(entities: List<ProductEntity>): List<ProductUIModel> {
        val res = mutableListOf<ProductUIModel>()
        entities.forEach {
            res.add(
                ProductUIModel(
                    it.productId,
                    it.productImage,
                    it.productName,
                    it.productPrice,
                    it.ratingAverage
                )
            )
        }
        return res
    }

    fun getProductsEntityFromResponse(productsResponse: ProductsResponse): List<ProductEntity> {
        val res = mutableListOf<ProductEntity>()
        productsResponse.data.searchProduct.products.forEach {
            res.add(
                ProductEntity(
                    0,
                    it.productId,
                    it.productName,
                    it.productPrice,
                    it.departmentId,
                    it.ratingAverage,
                    it.productImage
                )
            )
        }
        return res
    }

}