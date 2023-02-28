package com.example.chatapp.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth


class RegViewModel() : ViewModel() {

    private val auth = FirebaseAuth.getInstance()
    private val _errorPassword = MutableLiveData<Boolean>()

    fun signUp(email: String, password: String) : Task<AuthResult> {
        return auth.createUserWithEmailAndPassword(email, password)
    }
}