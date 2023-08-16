package com.example.storeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.navigation.ui.NavigationUI.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
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
//                R.id.your_orders -> {
//                    navController.navigate(R.id.ordersFragment)
//                    return@setOnItemSelectedListener true
                //}
                R.id.account ->{
                    navController.navigate(R.id.accountFragment)
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }
}