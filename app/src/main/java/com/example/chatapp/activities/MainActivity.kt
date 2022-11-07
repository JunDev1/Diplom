package com.example.chatapp.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.chatapp.databinding.ActivityMainBinding
import com.example.chatapp.fragments.AuthFragment
import com.example.chatapp.fragments.MessageListFragment
import com.example.chatapp.fragments.ProfileFragment
import com.example.chatapp.func.replaceFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.*
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.ktx.Firebase

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private var _binding : ActivityMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()
    }
}