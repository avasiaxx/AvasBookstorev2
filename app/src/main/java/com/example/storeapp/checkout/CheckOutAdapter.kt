package com.example.storeapp.checkout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.storeapp.data.models.CartItem
import com.example.storeapp.databinding.CheckoutItemBinding

class CheckOutAdapter: RecyclerView.Adapter<CheckOutAdapter.ViewHolder>(){

    private val values = mutableListOf<CartItem>()
    inner class ViewHolder(
        private val binding: CheckoutItemBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(cartItem: CartItem){

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            CheckoutItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = values.size

    fun setItems(items: List<CartItem>?){
        this.values.apply{
            clear()
            if (items != null){
                addAll(items)
            }
        }
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.bind(item)
    }
}