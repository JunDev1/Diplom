package com.example.chatapp.data

import android.os.Message
import android.util.Log
import android.widget.Toast
import com.example.chatapp.domain.AuthRepository
import com.example.chatapp.domain.model.User
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AuthRepositoryImpl : AuthRepository {

    override suspend fun login(email: String, password: String): FirebaseUser? {
        return if (FirebaseAuth.getInstance().currentUser != null) {
            FirebaseAuth.getInstance().currentUser
        } else {
            null
        }
    }

    override suspend fun signUp(email: String, password: String): Task<AuthResult> {
        return FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
    }

    override suspend fun getCurrentUser(): FirebaseUser? {
        return FirebaseAuth.getInstance().currentUser
    }

    override suspend fun logout() {
        return FirebaseAuth.getInstance().signOut()
    }
}