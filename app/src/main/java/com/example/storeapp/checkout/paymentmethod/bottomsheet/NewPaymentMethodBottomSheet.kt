package com.example.storeapp.checkout.paymentmethod.bottomsheet


import android.os.Bundle
import android.text.InputFilter
import android.view.View
import android.view.View.OnFocusChangeListener
import androidx.fragment.app.activityViewModels
import com.example.storeapp.R
import com.example.storeapp.checkout.paymentmethod.PaymentMethodViewModel
import com.example.storeapp.databinding.FragmentNewPaymentMethodBinding
import com.example.storeapp.domain.ValidatorImpl
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.CompositeDateValidator
import com.google.android.material.datepicker.DateValidatorPointBackward
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import javax.inject.Inject


@AndroidEntryPoint
class NewPaymentMethodBottomSheet : BottomSheetDialogFragment(R.layout.fragment_new_payment_method) {

    private var selectedDate : Long = 0

    private val checkoutViewModel: PaymentMethodViewModel by activityViewModels()

    @Inject
    lateinit var validator: ValidatorImpl

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentNewPaymentMethodBinding.bind(view)

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

        //Create a list of composite date validators to confine between today's date & 5 years from now
        val constraintsBuilder =
            CalendarConstraints.Builder()
                .setStart(getDate())
                .setEnd(getDate5YearsFromNow())
                .setValidator(
                    CompositeDateValidator.allOf(
                        listOf(
                            DateValidatorPointForward.from(getDate()),
                            DateValidatorPointBackward.before(getDate5YearsFromNow())
                        )
                    )
                )

        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select date")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .setCalendarConstraints(constraintsBuilder.build())
                .build()


        datePicker.addOnPositiveButtonClickListener {
            selectedDate = datePicker.selection!!


            val timeZoneUTC = TimeZone.getDefault()
            val offsetFromUTC = timeZoneUTC.getOffset(Date().time) * -1

            // Create a date format, then a date object with our offset

            // Create a date format, then a date object with our offset
            val simpleFormat = SimpleDateFormat("MM/yy", Locale.CANADA)
            val date = Date(selectedDate + offsetFromUTC)

            expDateTextInput.setText(simpleFormat.format(date))
        }

        expDateTextInput.onFocusChangeListener = OnFocusChangeListener { _, _ ->
                datePicker.show(childFragmentManager, "DatePicker")
        }
        binding.expDateInput.isClickable = true
        binding.expDateInput.setOnClickListener {
            datePicker.show(childFragmentManager, "DatePicker")
        }

        //Saves new payment method & closes fragment
        binding.save.setOnClickListener{
            val ccv: String = binding.ccvInput.text.toString()
            val cc: String = binding.ccInput.text.toString()
            val expDate: String = expDateTextInput.text.toString()
            val name: String = binding.nameInput.text.toString()
            var valid = true

            if(expDate.isEmpty()) {
                binding.ccDate.error = "Please select a date"
            } else {
                binding.ccDate.error = null
                valid = false
            }
            if(name.isEmpty()) {
                binding.name.error = "Please enter a valid name"
            } else {
                binding.name.error = null
                valid = false
            }
            if (!validator.checkCreditCard(cc) || cc.isEmpty()) {
                binding.creditCardNumber.error = "Improper Credit Card Number"
            } else {
                binding.creditCardNumber.error = null
                valid = false
            }
            if (!validator.checkCCV(ccv) || ccv.isEmpty()) {
                binding.ccv.error = "Improper CCV"
                valid = false
            } else {
                binding.ccv.error = null
            }
            if(valid) {
                createPaymentMethod(cc, expDate, ccv)
            }
        }
    }

    private fun createPaymentMethod(
        cc: String,
        expDate: String,
        ccv: String
    ) {
        checkoutViewModel.addNewPaymentMethod(
            3,
            cc,
            expDate,
            ccv.toInt()
        )
        dismiss()
    }

    private fun getDate5YearsFromNow(): Long {
        val today = Instant.now().toEpochMilli()
        val calendar = Calendar.getInstance(TimeZone.getDefault())
        calendar.timeInMillis = today
        calendar.add(Calendar.YEAR, 5)
        return calendar.timeInMillis
    }

    private fun getDate(): Long{
        val today = Instant.now().toEpochMilli()
        val calendar = Calendar.getInstance(TimeZone.getDefault())
        calendar.timeInMillis = today
        return calendar.timeInMillis
    }
}