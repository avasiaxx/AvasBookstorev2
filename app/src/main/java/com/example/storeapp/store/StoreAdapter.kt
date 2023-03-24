package com.example.storeapp.store

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.example.storeapp.databinding.FragmentStoreItemBinding



/**
 * [RecyclerView.Adapter] that can display a [StoreItem].
 * TODO: Replace the implementation with code for your data type.
 */
class StoreAdapter(
    private val onIncrease: (StoreItem) -> Unit,
    private val onDecrease: (StoreItem) -> Unit
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
        val idView: TextView = binding.itemDescription

        fun bind(storeItem: StoreItem){
            binding.itemTitle.text = storeItem.itemName
            binding.itemDescription.text = storeItem.itemDescription
            binding.itemImage.setImageResource(storeItem.imageResourceId)
            binding.quantityButtons.increaseQuantity.setOnClickListener {
                onIncrease(storeItem)
            }
            binding.quantityButtons.decreaseQuantity.setOnClickListener{
                onDecrease(storeItem)
            }
        }
        override fun toString(): String {
            return super.toString()
        }
    }

}