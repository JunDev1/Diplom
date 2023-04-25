package com.example.chatapp.data

import com.example.chatapp.domain.ChatAppRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

open class ChatAppRepositoryImpl : ChatAppRepository {

    val auth = FirebaseAuth.getInstance()
    override fun login(email: String, password: String): FirebaseUser? {
        return auth.currentUser
    }
    override fun getCurrentUser(): FirebaseUser? {
        return FirebaseAuth.getInstance().currentUser
    }
    override fun signUp(email: String, password: String): Task<AuthResult> {
        return auth.createUserWithEmailAndPassword(email, password)
    }
    override fun logout() {
        return FirebaseAuth.getInstance().signOut()
    }
}