package com.example.chatapp.domain

interface AuthRepository {
    suspend fun login(email: String, password: String): ViewState<Unit>
    suspend fun signUp(email: String, password: String): ViewState<Unit>
    suspend fun logout(): ViewState<Unit>
    suspend fun getCurrentUser(): ViewState<User?>
}