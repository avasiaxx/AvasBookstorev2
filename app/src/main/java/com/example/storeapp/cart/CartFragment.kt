package com.example.storeapp.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.storeapp.R
import com.example.storeapp.databinding.FragmentCartBinding

class CartFragment: Fragment(R.layout.fragment_cart) {

    private var _binding: FragmentCartBinding? = null
    private val binding
        get() = _binding!!

    private val cartViewModel: CartViewModel by viewModels()

    private lateinit var adapter: CartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}