package com.example.chatapp.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth


class RegViewModel() : ViewModel() {

    private val _errorPassword = MutableLiveData<Boolean>()
    val errorPassword : LiveData<Boolean>
    get() = _errorPassword

    private val _errorConfirmPassword = MutableLiveData<Boolean>()
    val errorConfirmPassword : LiveData<Boolean>
        get() = _errorConfirmPassword

    fun registrationFirebase(email: String, password: String) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
        Log.d("RegViewModel", "Registration successful")
    }
    fun validPassword(password: String, confirmPassword: String) : Boolean {
        var result = true
        if (password.isBlank() || confirmPassword.isBlank()) {
            _errorPassword.value = true
            _errorConfirmPassword.value = true
            result = false
        }
        return result
    }
}