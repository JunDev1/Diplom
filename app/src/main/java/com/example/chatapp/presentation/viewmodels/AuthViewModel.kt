package com.example.chatapp.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatapp.data.ChatAppRepositoryImpl
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch

class AuthViewModel
    (private val chatAppRepositoryImpl: ChatAppRepositoryImpl
    ) : ViewModel()  {
    fun login(email: String, password: String): MutableLiveData<FirebaseUser?> {
        val resultLiveData = MutableLiveData<FirebaseUser?>()
        viewModelScope.launch {
            resultLiveData.value = chatAppRepositoryImpl.login(email, password)
        }
        return resultLiveData
    }
}