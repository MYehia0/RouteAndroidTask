package com.example.routeandroidtask.ui.products

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.routeandroidtask.data.datasources.response.Product
import com.example.routeandroidtask.databinding.ItemProductBinding

class ProductAdapter (private var items: List<Product?>?) : Adapter<ProductAdapter.ProductHolder>() {

    private lateinit var binding: ItemProductBinding

    class ProductHolder(private val binding: ItemProductBinding) : ViewHolder(binding.root) {
        fun bindProduct(product: Product?) {
            binding.product = product
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        holder.bindProduct(items?.get(position))
    }

    fun changeData(products: List<Product?>?) {
        items = products
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items?.size?:0

}