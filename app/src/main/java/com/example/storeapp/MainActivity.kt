package com.example.storeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.viewbinding.ViewBinding
import com.example.storeapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val navController = findNavController(R.id.nav_host_fragment)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        setupActionBarWithNavController(navController)
        setupWithNavController(bottomNavigationView, navController)
        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> {
                    navController.navigate(R.id.storeFragment)
                    return@setOnItemSelectedListener true
                }
                R.id.cart -> {
                    navController.navigate(R.id.cartFragment)
                    return@setOnItemSelectedListener true
                }
                R.id.your_orders -> {
                    navController.navigate(R.id.ordersFragment)
                    return@setOnItemSelectedListener true
                }
                R.id.account ->{
                    navController.navigate(R.id.accountFragment)
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }
}