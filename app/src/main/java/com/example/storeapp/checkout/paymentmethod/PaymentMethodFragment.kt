package com.example.storeapp.checkout.paymentmethod

import android.os.Bundle
import android.text.InputFilter
import android.view.View
import android.view.View.OnFocusChangeListener
import com.example.storeapp.R
import com.example.storeapp.databinding.FragmentPaymentMethodBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone


class PaymentMethodFragment() : BottomSheetDialogFragment(R.layout.fragment_payment_method) {

    private var selectedDate : Long = 0
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentPaymentMethodBinding.bind(view)

        //Sets Max Length Values for Text Input for CCV & CC
        val ccvTextInput: TextInputEditText = view.findViewById(R.id.ccvInput)
        val ccTextInput: TextInputEditText = view.findViewById(R.id.ccInput)
        val expDateTextInput: TextInputEditText = view.findViewById(R.id.expDateInput)

        val ccvMaxLength = 3
        val ccMaxLength = 16
        val dateMaxLength = 7
        ccvTextInput.filters += InputFilter.LengthFilter(ccvMaxLength)
        ccTextInput.filters += InputFilter.LengthFilter(ccMaxLength)
        expDateTextInput.filters += InputFilter.LengthFilter(dateMaxLength)
        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select date")
                .build()


        datePicker.addOnPositiveButtonClickListener {
            selectedDate = datePicker.selection!!

            val timeZoneUTC = TimeZone.getDefault()
            val offsetFromUTC = timeZoneUTC.getOffset(Date().time) * -1

            // Create a date format, then a date object with our offset

            // Create a date format, then a date object with our offset
            val simpleFormat = SimpleDateFormat("MM/yyyy", Locale.CANADA)
            val date = Date(selectedDate + offsetFromUTC)

            expDateTextInput.setText(simpleFormat.format(date))
        }
        binding.save.setOnClickListener{
            dismiss()
        }


        expDateTextInput.onFocusChangeListener = OnFocusChangeListener { _, _ ->
                datePicker.show(childFragmentManager, "DatePicker")
        }
        binding.expDateInput.isClickable = true
        binding.expDateInput.setOnClickListener {
            datePicker.show(childFragmentManager, "DatePicker")
        }

    }
}