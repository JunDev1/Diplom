package com.example.chatapp.presentation.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.chatapp.R
import com.example.chatapp.databinding.ActivityMainBinding
import com.example.chatapp.presentation.fragments.MessageListFragment
import com.example.chatapp.presentation.fragments.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationItemView
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
        binding.bottomNavView.selectedItemId = R.id.item_profile
        val navController = setupNavController()
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.authFragment) {
                binding.bottomNavView.visibility = View.GONE
            } else if (destination.id == R.id.setNameSurnameFragment) {
                binding.bottomNavView.visibility = View.GONE
            } else if (destination.id == R.id.regFragment) {
                binding.bottomNavView.visibility = View.GONE
            } else {
                binding.bottomNavView.visibility = View.VISIBLE
            }
        }
        binding.bottomNavView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.item_profile -> {
                    replaceFragment(ProfileFragment())
                    true
                }
                R.id.item_chat -> {
                    replaceFragment(MessageListFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.main_container, fragment)
            .addToBackStack(null).commit()
    }

    private fun setupNavController(): NavController {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_container) as NavHostFragment
        val navController = navHostFragment.navController
        findViewById<BottomNavigationView>(R.id.bottomNavView).setupWithNavController(navController)
        return navController
    }
}