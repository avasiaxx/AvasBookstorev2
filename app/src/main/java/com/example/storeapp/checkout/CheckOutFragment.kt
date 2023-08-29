package com.example.storeapp.checkout

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.storeapp.R
import com.example.storeapp.databinding.FragmentCheckoutBinding
import com.example.storeapp.shared.addSwipeDeleteListener
import dagger.hilt.android.AndroidEntryPoint


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CheckOutFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

@AndroidEntryPoint
class CheckOutFragment : Fragment(R.layout.fragment_checkout) {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentCheckoutBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var adapter: CheckOutAdapter

    private val checkoutViewModel: CheckOutViewModel by activityViewModels()

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
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_checkout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCheckoutBinding.bind(view)
        adapter = CheckOutAdapter()
        binding.recyclerView.apply {
            layoutManager = when{
                columnCount <= 1 -> LinearLayoutManager(context)
                else -> GridLayoutManager(context, columnCount)
            }
            adapter = this@CheckOutFragment.adapter
            addSwipeDeleteListener {  position ->
                AlertDialog.Builder(context)
                    .setTitle("Delete Payment Method")
                    .setMessage("Are you sure you want to delete this payment method?")
                    // Specifying a listener allows you to take an action before dismissing the dialog.
                    // The dialog is automatically dismissed when a dialog button is clicked.
                    .setPositiveButton(android.R.string.ok
                    ) { _, _ ->
                        checkoutViewModel.deletePosition(position)
                        this@CheckOutFragment.adapter.notifyItemRemoved(position)
                        // Continue with delete operation
                    } // A null listener allows the button to dismiss the dialog and take no further action.
                    .setNegativeButton(android.R.string.cancel) { _, _ ->
                        this@CheckOutFragment.adapter.notifyItemChanged(position)
                    }
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show()
            }
        }
        checkoutViewModel.init()
        checkoutViewModel.items.observe(viewLifecycleOwner){
            adapter.setItems(it)
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
            CheckOutFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}