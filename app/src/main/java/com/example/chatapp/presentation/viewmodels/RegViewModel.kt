package com.example.chatapp.presentation.viewmodels

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chatapp.domain.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseUser


class RegViewModel(private val context: Context) : ViewModel() {
    private val _authUser = MutableLiveData<FirebaseUser?>()
    val authUser: LiveData<FirebaseUser?> = _authUser

    fun createUser(auth: FirebaseAuth, email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                val user = auth.currentUser
                _authUser.value = user
            } else {
                val exception = it.exception
                when (exception) {
                    is FirebaseAuthUserCollisionException -> {
                        Toast.makeText(context, "Пользователь уже существует", Toast.LENGTH_LONG)
                            .show()
                    }
                }
            }
        }
    }
}