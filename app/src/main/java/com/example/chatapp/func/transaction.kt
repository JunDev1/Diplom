package com.example.chatapp.func

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.chatapp.R

public fun AppCompatActivity.replaceFragment(fragment: Fragment) {
    val transaction = supportFragmentManager.beginTransaction()
        .replace(R.id.nav_host_fragment_container, fragment)
        .addToBackStack(null)
        .commit()
}

public fun Fragment.replaceFragment(fragment: Fragment) {
    val transaction = parentFragmentManager.beginTransaction()
        .replace(R.id.nav_host_fragment_container, fragment)
        .addToBackStack(null)
        .commit()
}