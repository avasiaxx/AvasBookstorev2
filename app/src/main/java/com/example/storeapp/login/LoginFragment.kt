package com.example.storeapp.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.storeapp.R
import com.example.storeapp.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment: Fragment(R.layout.fragment_login) {

    private var _binding: FragmentLoginBinding? = null
    private val binding
        get() = _binding!!

    private val loginViewModel: LoginViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val username = binding.username.text.toString()
        val password = binding.password.text.toString()
        val navController = Navigation.findNavController(view)
        _binding = FragmentLoginBinding.bind(view)
        binding.login.setOnClickListener {
            loginViewModel.validateLogin(username,password)
            loginViewModel.checkLoginState(username, password)
        }
    }
}