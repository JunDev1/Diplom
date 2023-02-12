package com.example.chatapp.presentation.viewmodels

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chatapp.domain.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class SetNameSurnameViewModel(private val application: Application) : ViewModel() {
    private val _username = MutableLiveData<String>()
    val username: LiveData<String> = _username
    private val _surname = MutableLiveData<String>()
    val surname: LiveData<String> = _surname

    private val firebase = FirebaseAuth.getInstance()
    fun sendDataToFirebase(username: String, surname: String) {
        if (username.isNotBlank() && surname.isNotBlank()) {
            _username.value = User().username!!
            _surname.value = User().surname!!
        } else {
            Toast.makeText(application.applicationContext, "Fields is blank", Toast.LENGTH_SHORT)
                .show()
        }
    }
}