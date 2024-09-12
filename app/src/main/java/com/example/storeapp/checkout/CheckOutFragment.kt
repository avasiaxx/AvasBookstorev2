package com.example.storeapp.checkout

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.storeapp.R
import com.example.storeapp.databinding.FragmentCheckoutBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CheckOutFragment : Fragment(R.layout.fragment_checkout) {

    private var _binding: FragmentCheckoutBinding? = null
    private val binding
        get() = _binding!!

    private val checkOutViewModel: CheckOutViewModel by activityViewModels()

    private lateinit var adapter: CheckOutAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = Navigation.findNavController(view)
        _binding = FragmentCheckoutBinding.bind(view)
        adapter = CheckOutAdapter()
        binding.orderRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@CheckOutFragment.adapter
        }
        checkOutViewModel.init()
        checkOutViewModel.items.observe(viewLifecycleOwner) {
            adapter.setItems(it)
        }
        if(checkOutViewModel.isLoggedIn && !checkOutViewModel.orderCreated){
            Snackbar.make(binding.root, "Successfully logged in",
                500)
                .setAction("Close") {}
                .show()
                checkOutViewModel.orderCreated = true
        }
        binding.itemsTotalDisplay.text = checkOutViewModel.getFormattedSubTotal()
        binding.shippingToDisplay.text = checkOutViewModel.getFormattedShippingCost()
        binding.taxAmountDisplay.text = checkOutViewModel.getFormattedTax()
        binding.orderTotalDisplay.text = checkOutViewModel.getFormattedTotal()
        binding.changeMethod.setOnClickListener {
            navController.navigate(R.id.paymentMethodFragment)
        }
        binding.address.text = checkOutViewModel.formatShippingAddress()
        checkOutViewModel.primaryPaymentMethod.observe(viewLifecycleOwner) {
            binding.paymentMethodFiller.text = checkOutViewModel.formatUserCC()
        }
        binding.FinishOrder.setOnClickListener {

        }
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    //Return to cart on back button
    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                findNavController().navigate(R.id.cartFragment)
                true
            }

            else -> {
                false
            }
        }
    }
}