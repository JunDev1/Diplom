package com.example.chatapp.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatapp.domain.AuthRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class AuthViewModel(private val authRepository: AuthRepository) : ViewModel()  {
    fun login(email: String, password: String): LiveData<Result<Unit>> {
        val resultLiveData = MutableLiveData<Result<Unit>>()
        viewModelScope.launch {
            resultLiveData.value = authRepository.login(email, password)
        }
        return resultLiveData
    }
}