package com.example.storeapp.account

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.storeapp.R
import com.example.storeapp.databinding.YourAccountFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountFragment: Fragment(R.layout.your_account_fragment) {

    private var columnCount = 2

    private var _binding: YourAccountFragmentBinding? = null
    private val binding
        get() = _binding!!

    private val accountViewModel: AccountViewModel by viewModels()

    private lateinit var adapter: AccountAdapter
    companion object{

        private const val ARG_COLUMN_COUNT = "column-count"

        @JvmStatic
        fun newInstance(columnCount: Int) =
            AccountFragment().apply{
                arguments = Bundle().apply{
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
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
        _binding = YourAccountFragmentBinding.bind(view)
        adapter = AccountAdapter()
        binding.recyclerView.apply{
            layoutManager = when{
                columnCount <= 1 -> LinearLayoutManager(context)
                else -> GridLayoutManager(context, columnCount)
            }
            adapter = this@AccountFragment.adapter
        }
        accountViewModel.init(requireContext())
        accountViewModel.items.value?.let { adapter.setItems(it) }
    }
}