package com.example.storeapp.cart

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.storeapp.R
import com.example.storeapp.checkout.CheckOutViewModel
import com.example.storeapp.databinding.FragmentCartListBinding
import com.example.storeapp.domain.CurrencyFormatter
import com.example.storeapp.domain.repositories.CartRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CartFragment : Fragment(R.layout.fragment_cart_list) {

    @Inject
    lateinit var currencyFormatter: CurrencyFormatter
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

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(false)
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
            binding.subtotal.text = getString(R.string.subtotal, currencyFormatter.formatCurrency(it))
        }
        cartViewModel.tax.observe(viewLifecycleOwner){
            binding.tax.text = getString(R.string.tax_13, currencyFormatter.formatCurrency(it))
        }
        cartViewModel.totalPrice.observe(viewLifecycleOwner){
            binding.total.text = getString(R.string.total, currencyFormatter.formatCurrency(it))
        }
        binding.checkout.setOnClickListener {
            val navController = findNavController(view)
            navController.navigate(R.id.checkOutFragment)
            cartViewModel.createNewOrder()
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {

        const val ARG_COLUMN_COUNT = "column-count"
    }
}