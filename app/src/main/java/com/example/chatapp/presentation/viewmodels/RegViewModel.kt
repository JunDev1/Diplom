package com.example.chatapp.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chatapp.data.ChatAppRepositoryImpl
import com.example.chatapp.domain.RegUseCase
import com.google.firebase.auth.FirebaseAuth


class RegViewModel(
    private val chatAppRepositoryImpl: ChatAppRepositoryImpl
) : ViewModel() {

    private val _email = MutableLiveData<String>()
    val email: MutableLiveData<String>
        get() = _email
    private val _password = MutableLiveData<String>()
    val password: MutableLiveData<String>
        get() = _password

    private val repository = ChatAppRepositoryImpl()

    private val regUseCase = RegUseCase(repository)

    private val auth = FirebaseAuth.getInstance()
    fun signUp(email: String, password: String) {
        _email.value = email
        _password.value = password
        regUseCase.signUpUseCase(_email, _password.toString())
    }
}