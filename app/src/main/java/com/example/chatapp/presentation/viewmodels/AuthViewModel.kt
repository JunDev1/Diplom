package com.example.chatapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class AuthViewModel : ViewModel() {
    //переменная,которая предоставляет экземпляр к FirebaseAuth

    fun signInWithEmail(email : String, password : String) : Task<AuthResult> {
        return auth.signInWithEmailAndPassword(email,password)
    }

    companion object {
        val auth = FirebaseAuth.getInstance()
    }
}