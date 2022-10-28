package com.example.chatapp.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.chatapp.databinding.ActivityMainBinding
import com.example.chatapp.fragments.AuthFragment
import com.example.chatapp.fragments.MessageListFragment
import com.example.chatapp.func.replaceFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.*

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var _binding : ActivityMainBinding
    private lateinit var user : FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)
    }

}