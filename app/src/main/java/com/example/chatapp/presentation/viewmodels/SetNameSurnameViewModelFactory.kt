package com.example.chatapp.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SetNameSurnameViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SetNameSurnameViewModel::class.java)) {
            return SetNameSurnameViewModel(application) as T
        } else {
            throw RuntimeException("Unknown modelClass $modelClass")
        }
    }
}