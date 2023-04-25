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
import com.example.chatapp.data.ChatAppRepositoryImpl
import com.example.chatapp.databinding.FragmentRegBinding
import com.example.chatapp.presentation.viewmodels.RegViewModel
import com.example.chatapp.presentation.viewmodels.ViewModelFactory

private const val TAG = "RegFragment"
private const val ARG_PARAM2 = "param2"

class RegFragment : Fragment() {

    private var _binding: FragmentRegBinding? = null
    private val binding get() = _binding!!
    private val viewModelFactory by lazy {
        ViewModelFactory(chatAppRepositoryImpl = ChatAppRepositoryImpl())
    }
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[RegViewModel::class.java]
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
        launchRegistration()
        launchAuth()
    }

    private fun launchAuth() {
        binding.backToAuthTv.setOnClickListener {
            findNavController().navigate(RegFragmentDirections.actionRegFragmentToAuthFragment())
            Log.d(TAG, "Back to auth fragment")
        }
    }

    private fun launchRegistration() {
        with(binding) {
            val email = viewModel.email.observe(viewLifecycleOwner) {
                binding.emailTfEt.text
            }
            val password = viewModel.password.observe(viewLifecycleOwner) {
                binding.passwordTfEt.text
            }
            signUpBtn.setOnClickListener {
                if (email.toString().isNotBlank() && password.toString().isNotBlank()) {
                    viewModel.signUp(email.toString(), password.toString())
                } else {
                    Toast.makeText(requireContext(), "Поля пустые", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}