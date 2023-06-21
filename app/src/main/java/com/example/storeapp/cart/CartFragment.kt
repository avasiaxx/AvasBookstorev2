package com.example.storeapp.cart

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.storeapp.R
import com.example.storeapp.databinding.FragmentCartListBinding
import com.example.storeapp.shared.Util.formatCurrency
import com.example.storeapp.store.StoreFragment

class CartFragment: Fragment(R.layout.fragment_cart_list) {

    private var columnCount = 1

    private var _binding: FragmentCartListBinding? = null
    private val binding
        get() = _binding!!

    private val cartViewModel: CartViewModel by activityViewModels()

    private lateinit var adapter: CartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let{
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Binding the fragment class binding
        _binding = FragmentCartListBinding.bind(view)
        adapter = CartAdapter(cartViewModel::removeFromCart, cartViewModel::onIncrease,
        cartViewModel::onDecrease)
        binding.recyclerView.apply {
            layoutManager = when {
                columnCount <= 1 -> LinearLayoutManager(context)
                else -> GridLayoutManager(context, columnCount)
            }
            adapter = this@CartFragment.adapter
        }
        cartViewModel.items.observe(viewLifecycleOwner) {
            adapter.setItems(it)
        }
        cartViewModel.subTotal.observe(viewLifecycleOwner){
            binding.subtotal.text = getString(R.string.subtotal, it.formatCurrency())
        }
        cartViewModel.tax.observe(viewLifecycleOwner){
            binding.tax.text = getString(R.string.tax_13, it.formatCurrency())
        }
        cartViewModel.totalPrice.observe(viewLifecycleOwner){
            binding.total.text = getString(R.string.total, it.formatCurrency())
        }
        binding.checkout.setOnClickListener(){

        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {

        const val ARG_COLUMN_COUNT = "column-count"
        @JvmStatic
        fun newInstance(columnCount: Int) =
            StoreFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}