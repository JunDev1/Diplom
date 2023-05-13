package com.example.chatapp.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.chatapp.R
import com.example.chatapp.databinding.FragmentAuthBinding
import com.example.chatapp.presentation.viewmodels.AuthViewModel
import com.google.firebase.auth.FirebaseAuth

private const val TAG = "AuthFragment"
private const val ARG_PARAM2 = "param2"


class AuthFragment : Fragment() {
    private val auth = FirebaseAuth.getInstance()
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
        if (auth.currentUser != null) {
            findNavController().navigate(AuthFragmentDirections.actionAuthFragmentToProfileFragment2())
        }
        with(binding) {
            signInBtn.setOnClickListener {
                launchSignIn()
            }
            signUpTv.setOnClickListener {
                signUp()
            }
        }
    }

    private fun signUp() {
        findNavController().navigate(AuthFragmentDirections.actionAuthFragmentToRegFragment())
    }

    private fun launchSignIn() {
        with(binding) {
            val email = emailTfEt.text.toString()
            val password = passwordTfEt.text.toString()
            if (email.isNotBlank() && password.isNotBlank()) {
                signIn(email, password)
            } else {
                Toast.makeText(requireContext(), R.string.auth_error, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            findNavController().navigate(AuthFragmentDirections.actionAuthFragmentToProfileFragment2())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}