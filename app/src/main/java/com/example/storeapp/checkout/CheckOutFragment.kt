package com.example.storeapp.checkout

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.storeapp.R
import com.example.storeapp.databinding.FragmentCheckoutBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CheckOutFragment: Fragment(R.layout.fragment_checkout) {

    private var _binding: FragmentCheckoutBinding? = null

    private val checkOutViewModel: CheckOutViewModel by viewModels()

    private lateinit var adapter: CheckOutAdapter
    private val binding
        get() = _binding!!

    private var columnCount = 1
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCheckoutBinding.bind(view)
        adapter = CheckOutAdapter()
        binding.orderRecyclerView.apply{
            layoutManager = when {
                columnCount <= 1 -> LinearLayoutManager(context)
                else -> GridLayoutManager(context, columnCount)
            }
            adapter = this@CheckOutFragment.adapter
        }
        checkOutViewModel.init()
        checkOutViewModel.items.observe(viewLifecycleOwner){
            adapter.setItems(it)
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    //Return to home on back button
    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            android.R.id.home -> {
                findNavController().popBackStack()
                true
            }
            else -> { false }
        }
    }
}