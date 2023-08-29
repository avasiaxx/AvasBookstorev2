package com.example.storeapp.checkout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.storeapp.R
import com.example.storeapp.data.models.PaymentInfo
import com.example.storeapp.databinding.PaymentItemBinding

class CheckOutAdapter: RecyclerView.Adapter<CheckOutAdapter.ViewHolder>() {

    private val values = mutableListOf<PaymentInfo>()

    private var previousBinding: PaymentItemBinding? = null

    private var isSelected: Boolean = false

    inner class ViewHolder(
        private val binding: PaymentItemBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(paymentInfo: PaymentInfo){
            binding.creditCardNumber.text = paymentInfo.card
            binding.expiryDate.text = binding.root.context.getString(R.string.expires, paymentInfo.expiry)
            binding.root.setOnClickListener {
                if(!isSelected) {
                    binding.root.setBackgroundColor(binding.root.context.getColor(R.color.selected))
                    binding.selected.setImageResource(R.drawable.baseline_check_circle_24)
                    isSelected = !isSelected
                    previousBinding?.root?.setBackgroundColor(binding.root.context.getColor(R.color.white))
                    previousBinding?.selected?.setImageResource(R.drawable.baseline_check_circle_outline_24)
                    previousBinding = binding
                } else{
                    previousBinding?.root?.setBackgroundColor(binding.root.context.getColor(R.color.white))
                    previousBinding?.selected?.setImageResource(R.drawable.baseline_check_circle_outline_24)
                    binding.root.setBackgroundColor(binding.root.context.getColor(R.color.selected))
                    binding.selected.setImageResource(R.drawable.baseline_check_circle_24)
                    previousBinding = binding
                }
            }
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