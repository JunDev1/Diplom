package com.example.chatapp.domain

import com.example.chatapp.domain.model.User

interface ProfileRepository {
    fun getNickname(nickname : String) : Result<User>
    fun getEmail(email : String) : Result<User>
    fun getPhoto(PhotoUrl : String) : Result<User>
}