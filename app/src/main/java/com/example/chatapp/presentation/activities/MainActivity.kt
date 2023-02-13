package com.example.chatapp.presentation.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.chatapp.R
import com.example.chatapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(TAG, "Created mainActivity")
        val navController = setupNavController()
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.authFragment) {
                binding.bottomNavView.visibility = View.GONE
            } else if (destination.id == R.id.setNameSurnameFragment) {
                binding.bottomNavView.visibility = View.GONE
            } else if (destination.id == R.id.regFragment) {
                binding.bottomNavView.visibility = View.GONE
            }
            else {
                binding.bottomNavView.visibility = View.VISIBLE
            }
        }
    }

    private fun setupNavController(): NavController {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_container) as NavHostFragment
        val navController = navHostFragment.navController
        findViewById<BottomNavigationView>(R.id.bottomNavView).setupWithNavController(navController)
        return navController
    }
}