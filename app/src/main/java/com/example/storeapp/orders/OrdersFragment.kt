package com.example.storeapp.orders

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.storeapp.R
import com.example.storeapp.databinding.YourOrdersFragmentBinding
import com.example.storeapp.store.StoreFragment

class OrdersFragment: Fragment(R.layout.your_orders_fragment){

    private var columnCount = 1

    private var _binding: YourOrdersFragmentBinding? = null

    private val binding
        get() = _binding!!

    private val ordersViewModel: OrdersViewModel by activityViewModels()

    private lateinit var adapter: OrdersAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let{
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = YourOrdersFragmentBinding.bind(view)
        binding.recyclerView.apply {
            layoutManager = when {
                columnCount <= 1 -> LinearLayoutManager(context)
                else -> GridLayoutManager(context, columnCount)
            }
            adapter = this@OrdersFragment.adapter
        }
    }

    companion object {

        const val ARG_COLUMN_COUNT = "column-count"
        @JvmStatic
        fun newInstance(columnCount: Int) =
            OrdersFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}

