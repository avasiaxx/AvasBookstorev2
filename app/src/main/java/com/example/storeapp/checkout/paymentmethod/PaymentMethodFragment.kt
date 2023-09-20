package com.example.storeapp.checkout.paymentmethod

import android.app.AlertDialog
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.storeapp.R
import com.example.storeapp.checkout.paymentmethod.bottomsheet.NewPaymentMethodBottomSheet
import com.example.storeapp.databinding.FragmentPaymentmethodsBinding
import com.example.storeapp.shared.addSwipeDeleteListener
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass.
 * Use the [PaymentMethodFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

@AndroidEntryPoint
class PaymentMethodFragment : Fragment(R.layout.fragment_paymentmethods) {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    // TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private val ARG_PARAM1 = "param1"
    private val ARG_PARAM2 = "param2"

    private var _binding: FragmentPaymentmethodsBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var adapter: PaymentMethodAdapter

    private val paymentMethodViewModel: PaymentMethodViewModel by activityViewModels()

    private var columnCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        setHasOptionsMenu(true)
    }
    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentPaymentmethodsBinding.bind(view)
        adapter = PaymentMethodAdapter()
        binding.recyclerView.apply {
            layoutManager = when{
                columnCount <= 1 -> LinearLayoutManager(context)
                else -> GridLayoutManager(context, columnCount)
            }
            adapter = this@PaymentMethodFragment.adapter
            addSwipeDeleteListener {  position ->
                AlertDialog.Builder(context)
                    .setTitle("Delete Payment Method")
                    .setMessage("Are you sure you want to delete this payment method?")
                    // Specifying a listener allows you to take an action before dismissing the dialog.
                    // The dialog is automatically dismissed when a dialog button is clicked.
                    .setPositiveButton(android.R.string.ok
                    ) { _, _ ->
                        paymentMethodViewModel.deletePosition(position)
                        this@PaymentMethodFragment.adapter.notifyItemRemoved(position)
                        // Continue with delete operation
                    } // A null listener allows the button to dismiss the dialog and take no further action.
                    .setNegativeButton(android.R.string.cancel) { _, _ ->
                        this@PaymentMethodFragment.adapter.notifyItemChanged(position)
                    }
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show()
            }
        }
        paymentMethodViewModel.init()
        paymentMethodViewModel.items.observe(viewLifecycleOwner){
            adapter.setItems(it)
        }
        binding.add.setOnClickListener{
            NewPaymentMethodBottomSheet().show(parentFragmentManager, "SomeTagHere")
        }
    }

    //Returning to home screen with back button
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

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CheckOutFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PaymentMethodFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}