package com.cesa.searchappcesa.search

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("app:items")
fun bindListToRecView(recView: RecyclerView, products: List<Product>?) {
    products?.let {
        (recView.adapter as ProductAdapter).submitList(it)
    }
}