package com.example.chatapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth


class RegViewModel() : ViewModel() {

    private lateinit var auth : FirebaseAuth

    fun registration() {
        auth = FirebaseAuth.getInstance()
        auth.createUserWithEmailAndPassword(toString(),toString()).addOnCompleteListener() {
            if (it.isSuccessful) {
            }
        }
    }
}