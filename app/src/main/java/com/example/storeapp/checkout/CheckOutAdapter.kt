package com.example.storeapp.checkout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.storeapp.R
import com.example.storeapp.data.models.PaymentInfo
import com.example.storeapp.databinding.PaymentItemBinding

class CheckOutAdapter(): RecyclerView.Adapter<CheckOutAdapter.ViewHolder>() {

    private val values = mutableListOf<PaymentInfo>()
    inner class ViewHolder(
        private val binding: PaymentItemBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(paymentInfo: PaymentInfo){
            binding.creditCardNumber.text = paymentInfo.card
            binding.expiryDate.text = binding.root.context.getString(R.string.expires, paymentInfo.expiry)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            PaymentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = values.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.bind(item)

    }

    fun setItems(items: List<PaymentInfo>?){
        this.values.apply{
            clear()
            if (items != null){
                addAll(items)
            }
        }
        notifyDataSetChanged()
    }
}