package com.example.chatapp.domain.model

import android.net.Uri
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class User(
    var nickname: String? = null,
    var email: String? = null,
    val userId : String? = null,
    val password : String? = null,
    val photoImageUrl : String? = null
)