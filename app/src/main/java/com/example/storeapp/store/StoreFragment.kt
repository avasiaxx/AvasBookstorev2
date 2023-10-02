package com.example.storeapp.store

import StoreAdapter
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.storeapp.R
import com.example.storeapp.cart.CartViewModel
import com.example.storeapp.databinding.FragmentStoreListBinding
import com.example.storeapp.domain.CurrencyFormatter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * A fragment representing a list of Items.
 */
@AndroidEntryPoint
class StoreFragment : Fragment(R.layout.fragment_store_list) {
    @Inject
    lateinit var currencyFormatter: CurrencyFormatter

    private var _binding: FragmentStoreListBinding? = null
    private val binding
        get() = _binding!!

    private val storeViewModel: StoreViewModel by viewModels()

    private val cartViewModel: CartViewModel by activityViewModels()

    private lateinit var adapter: StoreAdapter

    private val menuProvider by lazy {
        object : MenuProvider {
            var searchItem: MenuItem? = null
            var searchView: SearchView? = null
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.options_menu, menu)
                searchItem = menu.findItem(R.id.search)
                searchView = searchItem?.actionView as SearchView
                searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(p0: String?): Boolean {
                        return false
                    }

                    override fun onQueryTextChange(msg: String): Boolean {
                        // inside on query text change method we are
                        // calling a method to filter our recycler view.
                        storeViewModel.sortItems(msg)
                        return false
                    }
                })
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return true
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            COLUMN_COUNT = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Binding the fragment class binding
        _binding = FragmentStoreListBinding.bind(view)
        adapter = StoreAdapter(cartViewModel::onIncrease, currencyFormatter)
        binding.recyclerView.apply {
            layoutManager = when {
                COLUMN_COUNT <= 1 -> LinearLayoutManager(context)
                else -> GridLayoutManager(context, COLUMN_COUNT)
            }
            adapter = this@StoreFragment.adapter
        }
        storeViewModel.init()
        storeViewModel.items.observe(viewLifecycleOwner) {
            adapter.setItems(it)
        }
        activity?.addMenuProvider(menuProvider)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onPause() {
        super.onPause()
        activity?.removeMenuProvider(menuProvider)
    }

    companion object {
        const val ARG_COLUMN_COUNT = "column-count"
        private var COLUMN_COUNT = 2
    }
}