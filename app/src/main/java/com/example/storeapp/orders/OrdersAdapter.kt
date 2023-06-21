package com.example.storeapp.orders

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.storeapp.databinding.OrderItemFragmentBinding

class OrdersAdapter: RecyclerView.Adapter<OrdersAdapter.ViewHolder>() {

    private val values = mutableListOf<OrderItem>()
    inner class ViewHolder(
        private val binding: OrderItemFragmentBinding
    ): RecyclerView.ViewHolder(binding.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

}