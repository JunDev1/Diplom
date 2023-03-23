package com.example.chatapp.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.chatapp.databinding.FragmentRegBinding
import com.example.chatapp.presentation.viewmodels.AuthViewModel
import com.example.chatapp.presentation.viewmodels.RegViewModel

private const val TAG = "RegFragment"
private const val ARG_PARAM2 = "param2"

class RegFragment : Fragment() {

    private var _binding: FragmentRegBinding? = null
    private val binding get() = _binding!!
    private val viewModel by lazy {
        ViewModelProvider(this)[RegViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        launchRegistration()
        launchAuth()
    }

    private fun launchAuth() {
        binding.backToAuthTv.setOnClickListener {
            findNavController().navigate(RegFragmentDirections.actionRegFragmentToAuthFragment())
            Log.d(TAG, "Back to auth fragment")
        }
    }

//    private fun launchRegistration() {
//        with(binding) {
//            val email = binding.emailTfEt.text.toString()
//            val password = binding.passwordTfEt.text.toString()
//            signUpBtn.setOnClickListener {
//                viewModel.signUp(email, password)
//            }
//        }
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}