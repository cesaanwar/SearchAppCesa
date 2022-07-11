package com.cesa.searchappcesa.search

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cesa.searchappcesa.search.products.ProductAdapter
import com.cesa.searchappcesa.search.products.ProductUIModel
import com.cesa.searchappcesa.search.shops.ShopUIModel
import com.cesa.searchappcesa.search.shops.ShopsAdapter

@BindingAdapter("app:list_items")
fun <T> bindListToRecView(recView: RecyclerView, products: List<T>?) {
    products?.let {
        when (recView.adapter) {
            is ProductAdapter -> {
                (recView.adapter as ProductAdapter).submitList(it as List<ProductUIModel>)
            }
            is ShopsAdapter -> {
                (recView.adapter as ShopsAdapter).submitList(it as List<ShopUIModel>)
            }
        }
    }
}