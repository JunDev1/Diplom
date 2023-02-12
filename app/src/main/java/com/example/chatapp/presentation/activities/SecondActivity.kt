package com.example.chatapp.presentation.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.chatapp.R
import com.example.chatapp.databinding.ActivitySecondBinding

private const val TAG = "SecondActivity"

class SecondActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(TAG, "Created SecondActivity")
    }
}