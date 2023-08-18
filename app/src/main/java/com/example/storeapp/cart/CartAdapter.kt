package com.example.storeapp.cart

import android.icu.text.NumberFormat
import android.icu.util.Currency
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.storeapp.R
import com.example.storeapp.databinding.CartItemBinding

class CartAdapter(
    private val removeFromCart: (CartItem) -> Unit,
    private val onIncrease: (CartItem) -> Unit,
    private val onDecrease: (CartItem) -> Unit
): RecyclerView.Adapter<CartAdapter.ViewHolder>(){

    private val values = mutableListOf<CartItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return  ViewHolder(
            CartItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    fun setItems(items: List<CartItem>?){
        this.values.apply{
            clear()
            if (items != null) {
                addAll(items)
            }
        }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = values.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.bind(item)
    }

    inner class ViewHolder(
        //Create binding
        private val binding: CartItemBinding
        //Pass binding through view-holder constructor
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(cartItem: CartItem) {
            binding.itemImage.setImageResource(R.drawable.item1)
            binding.itemName.text = cartItem.storeItem.name
            binding.quantityNumber.text = cartItem.quantity.toString()
            binding.removeItem.setOnClickListener{
                removeFromCart(cartItem)
            }
            binding.increaseQuantity.setOnClickListener{
                onIncrease(cartItem)
            }
            binding.decreaseQuantity.setOnClickListener{
                onDecrease(cartItem)
            }
            val format: NumberFormat = NumberFormat.getCurrencyInstance()
            format.maximumFractionDigits = 4
            format.currency = Currency.getInstance("CAD")
            val formattedNumber: String = format.format(
                cartItem.storeItem.cost*cartItem.quantity)
            binding.itemPrice.text = formattedNumber
        }
    }
}