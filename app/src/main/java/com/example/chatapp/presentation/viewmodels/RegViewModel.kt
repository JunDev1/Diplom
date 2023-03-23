package com.example.chatapp.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatapp.domain.AuthRepository
import com.example.chatapp.domain.model.User
import kotlinx.coroutines.launch


class RegViewModel(private val authRepository: AuthRepository) : ViewModel() {
//    fun signUp(email: String, password: String): LiveData<Result<Unit>> {
//        val resultLiveData = MutableLiveData<Result<Unit>>()
//        viewModelScope.launch {
//            resultLiveData.value = authRepository.signUp(email, password)
//        }
//        return resultLiveData
//    }
//    fun login(email: String, password: String): LiveData<Result<Unit>> {
//        val resultLiveData = MutableLiveData<Result<Unit>>()
//        viewModelScope.launch {
//            resultLiveData.value = authRepository.login(email, password)
//        }
//        return resultLiveData
//    }
//
//    fun logout(): LiveData<Result<Unit>> {
//        val resultLiveData = MutableLiveData<Result<Unit>>()
//        viewModelScope.launch {
//            resultLiveData.value = authRepository.logout()
//        }
//        return resultLiveData
//    }
//
//    fun getCurrentUser(): LiveData<Result<User?>> {
//        val resultLiveData = MutableLiveData<Result<User?>>()
//        viewModelScope.launch {
//            resultLiveData.value = authRepository.getCurrentUser()
//        }
//        return resultLiveData
//    }
}