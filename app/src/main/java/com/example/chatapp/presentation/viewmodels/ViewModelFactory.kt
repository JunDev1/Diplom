package com.example.chatapp.presentation.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory(
    private val context : Context
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegViewModel::class.java)) {
            return RegViewModel(context) as T
        }
        throw RuntimeException("Unknown ViewModel $modelClass")
    }
}