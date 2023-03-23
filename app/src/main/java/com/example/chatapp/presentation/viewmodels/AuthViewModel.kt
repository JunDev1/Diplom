package com.example.chatapp.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatapp.data.AuthRepositoryImpl
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch

class AuthViewModel(private val authRepositoryImpl: AuthRepositoryImpl) : ViewModel()  {
    fun login(email: String, password: String): MutableLiveData<FirebaseUser?> {
        val resultLiveData = MutableLiveData<FirebaseUser?>()
        viewModelScope.launch {
            resultLiveData.value = authRepositoryImpl.login(email, password)
        }
        return resultLiveData
    }
}