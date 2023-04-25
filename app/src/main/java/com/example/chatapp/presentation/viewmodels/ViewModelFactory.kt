package com.example.chatapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.chatapp.data.ChatAppRepositoryImpl

class ViewModelFactory(private val chatAppRepositoryImpl: ChatAppRepositoryImpl) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegViewModel::class.java)) {
            return RegViewModel(chatAppRepositoryImpl) as T
        } else if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            return AuthViewModel(chatAppRepositoryImpl) as T
        }
        else {
            throw RuntimeException("Unknown viewModel")
        }
    }
}