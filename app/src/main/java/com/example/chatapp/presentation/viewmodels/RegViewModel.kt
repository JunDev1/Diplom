package com.example.chatapp.presentation.viewmodels

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chatapp.domain.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase


class RegViewModel(private val context: Context) : ViewModel() {
    private val databaseReference = FirebaseDatabase.getInstance().reference

    fun createUser(auth: FirebaseAuth, nickname : String,email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                val currentUser = auth.currentUser
                val userUid = currentUser?.uid

                if (userUid != null) {
                    val user = User(userUid, nickname)
                    val userRef = databaseReference.child("users").child(userUid)
                    userRef.setValue(user).addOnCompleteListener {
                        if (it.isSuccessful) {

                        } else {
                            Log.e("RegFragment", "Failed to write user data to Firebase", it.exception)
                        }
                    }
                } else {
                    Log.e("RegFragment", "Failed to register user in Firebase Authentication", it.exception)
                }

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