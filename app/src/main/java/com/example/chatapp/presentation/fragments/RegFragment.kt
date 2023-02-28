package com.example.chatapp.presentation.fragments

import  android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.chatapp.R
import com.example.chatapp.databinding.FragmentRegBinding
import com.example.chatapp.presentation.viewmodels.RegViewModel

private const val TAG = "RegFragment"
private const val ARG_PARAM2 = "param2"

class RegFragment : Fragment() {

    private var _binding: FragmentRegBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: RegViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[RegViewModel::class.java]
        launchRegistration()
        launchAuth()
    }

    private fun launchAuth() {
        binding.backToAuthTv.setOnClickListener {
            findNavController().navigate(RegFragmentDirections.actionRegFragmentToSetNameSurnameFragment())
            Log.d(TAG, "Back to auth fragment")
        }
    }

    private fun launchRegistration() {
        binding.signUpBtn.setOnClickListener {
            registration()
        }
    }

    private fun registration() {
        with(binding) {
            val email = emailTfEt.text.toString()
            val password = passwordTfEt.text.toString()
            val confirmPassword = confirmPasswordTfEt.text.toString()
            if (email.isNotBlank() && password.isNotBlank() && confirmPassword.isNotBlank() && password == confirmPassword) {
                viewModel.signUp(email, password)
                findNavController().navigate(RegFragmentDirections.actionRegFragmentToProfileFragment2())
            } else if (confirmPassword != password) {
                passwordTf.error = getString(R.string.password_dont_match)
                confirmPasswordTf.error = getString(R.string.password_dont_match)
            } else if (password.isBlank() && confirmPassword.isBlank()) {
                passwordTf.error = getString(R.string.fields_blank)
                confirmPasswordTf.error = getString(R.string.fields_blank)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}