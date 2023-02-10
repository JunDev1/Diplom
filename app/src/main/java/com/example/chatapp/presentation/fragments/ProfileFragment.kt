package com.example.chatapp.presentation.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import com.example.chatapp.R
import com.example.chatapp.databinding.FragmentProfileBinding
import com.example.chatapp.func.replaceFragment
import com.example.chatapp.presentation.viewmodels.ProfileViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.Observer

private const val TAG = "ProfileFragment"

open class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        binding.bottomNavView.selectedItemId = R.id.item_profile
        binding.bottomNavView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.item_chat -> {
                    replaceFragment(MessageListFragment())
                    Log.d(TAG, "Transaction replaced")
                    true
                }
                else -> false
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val username = binding.usernameTV
        val exit = binding.exitTV

        val usernameObserver = Observer<String> {
            username.text = it.toString()
            Log.d(TAG, "Data takes successful")
        }
        viewModel.gettingDataFromDB().observe(
            viewLifecycleOwner,
            usernameObserver
        )  //activity as LifecycleOwner - 1ый параметр

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

