package com.example.storeapp.store

import android.graphics.Typeface
import android.icu.text.NumberFormat
import android.icu.util.Currency
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.storeapp.R
import com.example.storeapp.cart.CartItem
import com.example.storeapp.databinding.FragmentStoreItemBinding
import com.google.android.material.snackbar.Snackbar
import com.example.storeapp.shared.Util
import com.example.storeapp.shared.Util.formatCurrency


/**
 * [RecyclerView.Adapter] that can display a [StoreItem].
 */
class StoreAdapter(
    private val onIncrease: (CartItem) -> Unit
) : RecyclerView.Adapter<StoreAdapter.ViewHolder>() {

    private val values = mutableListOf<StoreItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentStoreItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = values.size

    fun setItems(items: List<StoreItem>){
        this.values.apply {
            clear()
            addAll(items)
        }
        notifyDataSetChanged()
    }

    inner class ViewHolder(
        private val binding: FragmentStoreItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(storeItem: StoreItem){
            binding.itemTitle.text = storeItem.itemName
            binding.itemDescription.text = storeItem.itemDescription
            binding.itemImage.setImageResource(storeItem.imageResourceId)
            binding.itemPrice.text = storeItem.itemPrice.formatCurrency()
            binding.addToCart.setOnClickListener{
                val cartItem: CartItem = CartItem(storeItem, 1, 1)
                onIncrease(cartItem)
                val suffix = "added to cart"
                val spannable = SpannableStringBuilder("${storeItem.itemName} $suffix")
                spannable.setSpan(ForegroundColorSpan(binding.root.context.getColor(R.color.teal_700)),
                    0, storeItem.itemName.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                spannable.setSpan(StyleSpan(Typeface.BOLD), 0, storeItem.itemName.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                Snackbar.make(binding.root, spannable, 300).
                setAction("Close"){}
                    .show()
            }
        }
        override fun toString(): String {
            return super.toString()
        }
    }
}