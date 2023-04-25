package com.example.chatapp.domain

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser

interface ChatAppRepository {
    fun login(email : String, password : String) : FirebaseUser?
    fun signUp(email : String, password : String) : Task<AuthResult>
    fun getCurrentUser(): FirebaseUser?
    fun logout()

}