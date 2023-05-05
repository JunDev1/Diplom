package com.example.chatapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class AuthViewModel() : ViewModel() {

    private val auth = FirebaseAuth.getInstance()

    fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
    }
}