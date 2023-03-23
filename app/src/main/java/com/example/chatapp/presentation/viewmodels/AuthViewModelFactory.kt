package com.example.chatapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.chatapp.data.AuthRepositoryImpl

class AuthViewModelFactory(private val authRepository: AuthRepositoryImpl) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            return AuthViewModel(authRepository) as T
        } else
        {
            throw RuntimeException("Unknown viewModel")
        }
    }
}