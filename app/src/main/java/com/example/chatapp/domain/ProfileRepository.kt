package com.example.chatapp.domain

import com.example.chatapp.domain.model.User
import com.google.firebase.auth.FirebaseUser

interface ProfileRepository {
    fun getNickname(nickname : String) : Result<User>
    fun getEmail(email : String) : Result<User>
    fun getPhoto(PhotoUrl : String) : Result<User>
    suspend fun getCurrentUser() : FirebaseUser?
    suspend fun logout()
}