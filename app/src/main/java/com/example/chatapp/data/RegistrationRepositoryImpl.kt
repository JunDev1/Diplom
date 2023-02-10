package com.example.chatapp.data

import com.example.chatapp.domain.RegistrationRepository
import com.example.chatapp.domain.User
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import kotlin.math.E

private lateinit var firebase : FirebaseAuth

object RegistrationRepositoryImpl : RegistrationRepository {

    override fun createNewUser(user: User) {
        val email = user.email
        val password = user.password
        if ((email?.isBlank() ?: password?.isBlank()) as Boolean) {
            firebase.createUserWithEmailAndPassword(email.toString(), password.toString())
        } else throw RuntimeException("Unsuccessful create new user")
    }
}