package com.example.chatapp.domain.viewmodels.firebase

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class User(
    val name: String? = null,
    val surname: String? = null,
    val email: String? = null,
    val userId : String? = null
)
