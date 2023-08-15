package com.example.storeapp.orders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.storeapp.cart.CartItem
import com.example.storeapp.databinding.OrderItemFragmentBinding
import com.example.storeapp.databinding.YourOrdersFragmentItemBinding
import java.lang.IllegalStateException

class OrdersAdapter(
    private val newOrder: (CartItem, OrderHeader) -> Unit
        ): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    enum class ViewType {
        ORDER_HEADER, ORDER_ITEM
    }
    private val values = mutableListOf<Any>()
    inner class ViewHolderOrderItem(
        private val binding: OrderItemFragmentBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(cartItem: CartItem){
            binding.itemImage.setImageResource(cartItem.storeItem.imageResourceId)
            binding.itemName.text = cartItem.storeItem.itemName
            binding.quantityNumber.text = cartItem.quantity.toString()
        }
    }

    inner class ViewHolderEntireOrderItem(
        private val binding: YourOrdersFragmentItemBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(orderHeader: OrderHeader){
            binding.eta.text = orderHeader.deliveryTime
            binding.orderNumber.text = orderHeader.orderNum.toString()
            binding.total.text = orderHeader.total.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (ViewType.values()[viewType]) {
            ViewType.ORDER_HEADER -> ViewHolderEntireOrderItem(
                YourOrdersFragmentItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            ViewType.ORDER_ITEM -> ViewHolderOrderItem(
                OrderItemFragmentBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false)
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = values[position]
        return when(item){
            is CartItem -> ViewType.ORDER_ITEM
            is OrderHeader -> ViewType.ORDER_HEADER
            else -> throw IllegalStateException()
        }.ordinal
    }

    override fun getItemCount(): Int = values.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = values[position]
    }
}