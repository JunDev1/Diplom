package com.example.chatapp.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.chatapp.databinding.FragmentAuthBinding
import com.example.chatapp.presentation.viewmodels.AuthViewModel

private const val TAG = "AuthFragment"
private const val ARG_PARAM2 = "param2"


class AuthFragment : Fragment() {
    private val viewModel by lazy {
        ViewModelProvider(this)[AuthViewModel::class.java]
    }
    private var _binding: FragmentAuthBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            signInBtn.setOnClickListener {
                signIn()
            }
            signUpTv.setOnClickListener {
                signUp()
            }
        }
    }

    private fun signUp() {
        findNavController().navigate(AuthFragmentDirections.actionAuthFragmentToRegFragment())

    }

    private fun signIn() {
        with(binding) {
            val email = emailTfEt.text.toString()
            val password = passwordTfEt.text.toString()
            viewModel.signIn(email, password)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}