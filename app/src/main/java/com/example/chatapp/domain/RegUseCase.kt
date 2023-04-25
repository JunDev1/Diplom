package com.example.chatapp.domain

import com.example.chatapp.data.ChatAppRepositoryImpl
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class RegUseCase(private val chatAppRepository: ChatAppRepository) : ChatAppRepositoryImpl() {
    fun signUpUseCase(email: String, password: String): Task<AuthResult> {
        val auth = FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                chatAppRepository.signUp(email, password)
            }
        return auth
    }
}