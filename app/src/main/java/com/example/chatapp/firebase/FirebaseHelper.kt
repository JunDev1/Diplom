package com.example.chatapp.firebase

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class FirebaseHelper {
    fun getInfoAboutUser() {
        val user = Firebase.auth.currentUser
        user?.let {
            val name = user.displayName
            val email = user.email
            val photoUrl = user.photoUrl

            val emailVerified = user.isEmailVerified

            val uid = user.uid
        }
    }
}