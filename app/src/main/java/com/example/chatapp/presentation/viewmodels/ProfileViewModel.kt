package com.example.chatapp.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatapp.domain.ProfileRepository
import com.example.chatapp.domain.model.User
import kotlinx.coroutines.launch

const val TAG = "ProfileViewModel"

open class ProfileViewModel(private val profileRepository : ProfileRepository) : ViewModel() {

    fun getNickname(nickname: String) : LiveData<Result<User>> {
        val resultLiveData = MutableLiveData<Result<User>>()
        viewModelScope.launch {
            resultLiveData.value = profileRepository.getNickname(nickname)
        }
        return resultLiveData
    }

    fun getEmail(email : String) : LiveData<Result<User>> {
        val resultLiveData = MutableLiveData<Result<User>>()
        viewModelScope.launch {
            resultLiveData.value = profileRepository.getEmail(email)
        }
        return resultLiveData
    }

    fun getPhoto(photoUrl: String) : LiveData<Result<User>> {
        val resultLiveData = MutableLiveData<Result<User>>()
        viewModelScope.launch {
            resultLiveData.value = profileRepository.getPhoto(photoUrl)
        }
        return resultLiveData
    }
//    fun gettingDataFromDB(): LiveData<String> {
//        val uid = dbRef.push().key!!
//        dbRef.orderByChild(uid).addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                for (postSnapshot in snapshot.children) {
//                    Log.i(TAG, "Getting data")
//                    val data : User? = postSnapshot.getValue(User::class.java)
//                    getName.postValue(data!!.nickname.toString())
//                }
//
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                Log.d(TAG, "loadPost: Error", error.toException())
//            }
//        })
//        return getName
//    }

    override fun onCleared() {
        super.onCleared()
    }
}