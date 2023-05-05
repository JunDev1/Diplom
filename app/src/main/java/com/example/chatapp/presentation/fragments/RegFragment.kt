package com.example.chatapp.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.map
import androidx.navigation.fragment.findNavController
import com.example.chatapp.databinding.FragmentRegBinding
import com.example.chatapp.presentation.viewmodels.RegViewModel
import com.example.chatapp.presentation.viewmodels.ViewModelFactory
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

private const val TAG = "RegFragment"

class RegFragment : Fragment() {

    private var _binding: FragmentRegBinding? = null
    private val binding get() = _binding!!
    private val auth = FirebaseAuth.getInstance()
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[RegViewModel::class.java]
    }
    private val viewModelFactory by lazy {
        ViewModelFactory(requireContext())
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
        }
    }

    private fun launchRegistration() {
        with (binding) {
            signUpBtn.setOnClickListener {

                val email = emailTfEt.text.toString()
                val password = passwordTfEt.text.toString()
                if (email.isNotBlank() && password.isNotBlank()) {
                    viewModel.createUser(auth, email, password)
                    val nickname = viewModel.authUser
                    setNickname(nickname)
                    findNavController().navigate(RegFragmentDirections.actionRegFragmentToProfileFragment2())
                } else {
                    Toast.makeText(requireActivity(), "Ваши поля пустые", Toast.LENGTH_LONG).show()
                }
            }
        }

    }

    private fun setNickname(nickname: LiveData<FirebaseUser?>) {
        nickname.observe(viewLifecycleOwner) {
            binding.nicknameTfEt.text.toString()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
    }
}