package com.example.chatapp.domain

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class User(
    val username: String? = null,
    val surname: String? = null,
    val email: String? = null,
    val userId : String? = null,

    val password : String? = null,
)