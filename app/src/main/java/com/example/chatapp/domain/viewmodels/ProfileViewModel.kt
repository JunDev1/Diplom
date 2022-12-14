package com.example.chatapp.domain.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chatapp.domain.viewmodels.firebase.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue

const val TAG = "ProfileViewModel"

open class ProfileViewModel : ViewModel() {

    private val dbRef = FirebaseDatabase.getInstance().getReference("Users")
    private val uid = FirebaseAuth.getInstance().currentUser!!.uid

    val getName: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun gettingDataFromDB(): LiveData<String> {
        //Log.i(TAG, "Getting data")
        val uid = dbRef.push().key!!
        dbRef.orderByChild(uid).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (postSnapshot in snapshot.children) {
                    Log.i(TAG, "Getting data")
                    val data : User? = postSnapshot.getValue(User::class.java)
                    getName.postValue(data!!.name.toString())
                }

            }

            override fun onCancelled(error: DatabaseError) {
                Log.d(TAG, "loadPost: Error", error.toException())
            }
        })
        return getName
    }

    override fun onCleared() {
        super.onCleared()
    }
}