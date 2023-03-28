package com.example.storeapp.store

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.storeapp.R
import com.example.storeapp.cart.CartViewModel
import com.example.storeapp.databinding.FragmentStoreListBinding

/**
 * A fragment representing a list of Items.
 */
class StoreFragment : Fragment(R.layout.fragment_store_list) {

    private var columnCount = 2

    private var _binding: FragmentStoreListBinding? = null
    private val binding
        get() = _binding!!

    private val storeViewModel: StoreViewModel by viewModels()

    private lateinit var adapter: StoreAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.options_menu, menu)

        val searchItem: MenuItem = menu.findItem(R.id.search)

        val searchView: SearchView = searchItem.actionView as SearchView

        // below line is to call set on query text listener method.
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Binding the fragment class binding
        _binding = FragmentStoreListBinding.bind(view)
        adapter = StoreAdapter(storeViewModel::onIncrease,
            storeViewModel::onDecrease)
        binding.recyclerView.apply {
            layoutManager = when {
                columnCount <= 1 -> LinearLayoutManager(context)
                else -> GridLayoutManager(context, columnCount)
            }
            adapter = this@StoreFragment.adapter
        }
        storeViewModel.init(requireContext())
        storeViewModel.items.observe(viewLifecycleOwner) {
            adapter.setItems(it)
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            StoreFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}