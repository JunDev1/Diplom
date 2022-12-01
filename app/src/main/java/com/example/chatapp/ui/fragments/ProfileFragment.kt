package com.example.chatapp.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import com.example.chatapp.R
import com.example.chatapp.databinding.FragmentProfileBinding
import com.example.chatapp.domain.viewmodels.firebase.User
import com.example.chatapp.domain.viewmodels.firebase.database
import com.example.chatapp.func.replaceFragment
import com.example.chatapp.domain.viewmodels.ProfileViewModel
import com.example.chatapp.ui.activities.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

private const val TAG = "ProfileFragment"

open class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val dbRef = FirebaseDatabase.getInstance().getReference("Users")
    private val viewModel : ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val username = binding.usernameTV
        val exit = binding.exitTV

        val usernameObserver = Observer<String> {
            username.text = it.toString()
            Log.d(TAG,"Data takes successful")
        }
        viewModel.gettingDataFromDB().observe(viewLifecycleOwner, usernameObserver)  //activity as LifecycleOwner - 1ый параметр

//        username.text = viewModel.gettingDataFromDB().toString()


        exit.setOnClickListener {
            Firebase.auth.signOut().apply {
                replaceFragment(AuthFragment())
            }
        }
    }

    override fun onStart() {
        super.onStart()
        //запустить наблюдателя
    }

    override fun onDestroy() {
        super.onDestroy()
        //удалить наблюдателя
    }
}

