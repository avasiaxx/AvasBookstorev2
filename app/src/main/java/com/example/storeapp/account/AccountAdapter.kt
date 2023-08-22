package com.example.storeapp.account


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.storeapp.databinding.YourAccountItemBinding

class AccountAdapter: RecyclerView.Adapter<AccountAdapter.ViewHolder>() {

    private val values = mutableListOf<AccountItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            YourAccountItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AccountAdapter.ViewHolder, position: Int) {
        val item = values[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = values.size

    fun setItems(items: List<AccountItem>){
        this.values.apply{
            clear()
            addAll(items)
        }
        notifyDataSetChanged()
    }

    inner class ViewHolder(
        private val binding: YourAccountItemBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(accountItem: AccountItem){
            binding.title.text = accountItem.title
            binding.icon.setImageResource(accountItem.imageResourceId)
            binding.description.text = accountItem.itemDescription
            binding.materialCardView.setOnClickListener {
            }
        }
    }
}