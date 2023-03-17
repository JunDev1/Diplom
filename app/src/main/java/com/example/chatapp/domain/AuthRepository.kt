package com.example.chatapp.domain

import com.example.chatapp.domain.model.User

interface AuthRepository {
    suspend fun login(email : String, password : String) : Result<Unit>
    suspend fun signUp(email : String, password : String) : Result<Unit>
    suspend fun getCurrentUser() : Result<User?>
    suspend fun logout() : Result<Unit>

}