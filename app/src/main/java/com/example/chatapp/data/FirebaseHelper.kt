package com.example.chatapp.data

import com.example.chatapp.domain.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FirebaseHelper {
    fun writeNewUser(userId : String, nickname : String, email : String) {
        val user = User(nickname, email, userId)
        Firebase.database.reference.child("users").child(userId).setValue(user)
    }
}