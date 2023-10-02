package com.example.storeapp

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.storeapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val bottomNavigationView = binding.bottomNavigation
        setupActionBarWithNavController(navController)
        setupWithNavController(bottomNavigationView, navController)
        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    navController.navigate(R.id.storeFragment)
                    supportActionBar?.title = "Ava's Bookstore"
                    return@setOnItemSelectedListener true
                }

                R.id.cart -> {
                    navController.navigate(R.id.cartFragment)
                    Activity().title = "Your Cart"
                    return@setOnItemSelectedListener true
                }
//                R.id.your_orders -> {
//                    navController.navigate(R.id.ordersFragment)
//                    return@setOnItemSelectedListener true
                //}
//                R.id.account ->{
//                    navController.navigate(R.id.accountFragment)
//                    Activity().title = "Your Account"
//                    return@setOnItemSelectedListener true
//                }
            }
            false
        }
        //Disable bottom navigation on checkout/payment methods screens
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.newPaymentMethodFragment ||
                destination.id == R.id.checkOutFragment ||
                destination.id == R.id.paymentMethodFragment ||
                destination.id == R.id.loginFragment
            ) {
                bottomNavigationView.visibility = View.GONE
            } else {

                bottomNavigationView.visibility = View.VISIBLE
            }
        }
    }
}