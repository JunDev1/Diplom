package com.example.chatapp.domain.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chatapp.domain.viewmodels.firebase.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

const val TAG = "ProfileViewModel"

open class ProfileViewModel : ViewModel() {

    private val dbRef = FirebaseDatabase.getInstance().getReference("Users")

    val getName : MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

//    fun gettingDataFromDB(onReceived : (String) -> Unit )  {
//        dbRef.database.getReference("Users/username").addValueEventListener(object :
//            ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                Log.d(TAG,"DataChange current work")
//                onReceived(snapshot.getValue(User::class.java)?.name.toString())
//                val data = snapshot.getValue(User::class.java)
//                getName.postValue(
//                    data.toString()
//                )
//            }
//            override fun onCancelled(error: DatabaseError) {
//                Log.d(TAG, "loadPost: Error", error.toException())
////                Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT) requireContext - не должен быть во VM
//            }
//        })
//    }

    fun gettingDataFromDB() : LiveData<String>  {
        if(getName.value == null) {
            FirebaseDatabase.getInstance().getReference("Users/username")
                .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        val data = snapshot.getValue(User::class.java)
                        getName.postValue(data!!.name.toString())
                    }
//                    Log.d(TAG, "DataChange current work")
//                    val data = snapshot.getValue(User::class.java)
//                    getName.postValue(
//                        data.toString()
//                    )
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.d(TAG, "loadPost: Error", error.toException())
//                Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT) requireContext - не должен быть во VM
                }
            })
        }
        return getName
    }

    override fun onCleared() {
        super.onCleared()
    }
}