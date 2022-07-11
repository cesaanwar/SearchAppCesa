package com.cesa.searchappcesa.search.shops

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cesa.searchappcesa.databinding.ItemSearchShopBinding

class ShopsAdapter: ListAdapter<ShopUIModel, ShopsAdapter.ShopsViewHolder>(ShopDiffCallback()) {

    class ShopsViewHolder(val binding: ItemSearchShopBinding): RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): ShopsViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemSearchShopBinding.inflate(layoutInflater, parent, false)
                return ShopsViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopsViewHolder {
        return ShopsViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ShopsViewHolder, position: Int) {
        val shop = getItem(position)
        holder.binding.shop = shop
        Glide.with(holder.itemView).load(shop.shopImage).into(holder.binding.ivShop)
    }

}

class ShopDiffCallback: DiffUtil.ItemCallback<ShopUIModel>() {
    override fun areItemsTheSame(oldItem: ShopUIModel, newItem: ShopUIModel): Boolean {
        return oldItem.shopId == newItem.shopId
    }

    override fun areContentsTheSame(oldItem: ShopUIModel, newItem: ShopUIModel): Boolean {
        return newItem == oldItem
    }

}