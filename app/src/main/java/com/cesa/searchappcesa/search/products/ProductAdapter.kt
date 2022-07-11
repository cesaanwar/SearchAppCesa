package com.cesa.searchappcesa.search.products

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cesa.searchappcesa.R
import com.cesa.searchappcesa.databinding.ItemSearchLayoutBinding
import com.cesa.searchappcesa.search.SearchViewModel

class ProductAdapter(private val viewModel: SearchViewModel): ListAdapter<ProductUIModel, ProductAdapter.ProductViewHolder>(ProductDiffCallback()) {

    class ProductViewHolder(val itemBinding: ItemSearchLayoutBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        companion object {
            fun from(parent: ViewGroup): ProductViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemSearchLayoutBinding.inflate(layoutInflater, parent, false)
                return ProductViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = getItem(position)
        holder.itemBinding.product = product
        Glide.with(holder.itemView)
            .load(product.productImage)
            .placeholder(R.drawable.ic_placeholder)
            .into(holder.itemBinding.ivProduct)
        holder.itemBinding.executePendingBindings()
    }

}

class ProductDiffCallback: DiffUtil.ItemCallback<ProductUIModel>() {
    override fun areItemsTheSame(oldItem: ProductUIModel, newItem: ProductUIModel): Boolean {
        return oldItem.productId == newItem.productId
    }

    override fun areContentsTheSame(oldItem: ProductUIModel, newItem: ProductUIModel): Boolean {
        return oldItem.productId == newItem.productId
    }
}