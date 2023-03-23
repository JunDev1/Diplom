package com.example.chatapp.domain

import com.example.chatapp.domain.model.User
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    suspend fun login(email : String, password : String) : FirebaseUser?
    suspend fun signUp(email : String, password : String) : Task<AuthResult>
    suspend fun getCurrentUser() : FirebaseUser?
    suspend fun logout()

}