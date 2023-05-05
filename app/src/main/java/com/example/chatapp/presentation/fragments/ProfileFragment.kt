package com.example.chatapp.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.chatapp.databinding.FragmentProfileBinding
import com.example.chatapp.presentation.viewmodels.ProfileViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

private const val TAG = "ProfileFragment"

class ProfileFragment : Fragment() {
    private val auth = FirebaseAuth.getInstance()
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val viewModel by lazy {
        ViewModelProvider(this)[ProfileViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        launchSignOut()
        //binding.nicknameTV.text = profileInfo()
    }

    private fun profileInfo() : String? {
        val currentUser = auth.currentUser
        return currentUser!!.displayName
        Log.d("ProfileFragment", "${currentUser!!.displayName}")
    }

    private fun launchSignOut() {
        binding.exitTV.setOnClickListener {
            auth.signOut()
            findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToAuthFragment())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}

