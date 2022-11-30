package com.example.chatapp.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.chatapp.firebase.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

const val TAG = "ProfileViewModel"

open class ProfileViewModel : ViewModel() {


    private val dbRef = FirebaseDatabase.getInstance().getReference("Users")

    fun gettingDataFromDB() {
        dbRef.database.getReference("Users/username").addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
//                Log.d(TAG,"DataChange current work")
                val post = snapshot.getValue(User::class.java)
                post?.name.toString()

            }
            override fun onCancelled(error: DatabaseError) {
                Log.d(TAG, "loadPost: Error", error.toException())
//                Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT) requireContext - не должен быть во VM
            }
        })
    }

    override fun onCleared() {
        super.onCleared()
    }
}