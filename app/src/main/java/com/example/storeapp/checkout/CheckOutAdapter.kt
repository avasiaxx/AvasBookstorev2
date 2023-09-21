package com.example.storeapp.checkout

import android.icu.text.NumberFormat
import android.icu.util.Currency
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.storeapp.R
import com.example.storeapp.data.models.CartItem
import com.example.storeapp.databinding.CheckoutItemBinding

class CheckOutAdapter: RecyclerView.Adapter<CheckOutAdapter.ViewHolder>(){

    private val values = mutableListOf<CartItem>()

    inner class ViewHolder(
        private val binding: CheckoutItemBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(cartItem: CartItem){
            binding.itemName.text = cartItem.storeItem.name
            val format: NumberFormat = NumberFormat.getCurrencyInstance()
            format.maximumFractionDigits = MAX_FRACTION_DIGITS
            format.currency = Currency.getInstance("CAD")
            val formattedNumber: String = format.format(
                cartItem.storeItem.cost*cartItem.quantity)
            binding.itemPrice.text = formattedNumber
            binding.quantityNumber.text = cartItem.quantity.toString()
            binding.itemImage.setImageResource(R.drawable.item1)
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

    companion object {
        private const val MAX_FRACTION_DIGITS = 2
    }
}