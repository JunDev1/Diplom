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

private const val TAG = "AuthFragment"
private const val ARG_PARAM2 = "param2"


class AuthFragment : Fragment() {
    private var _binding: FragmentAuthBinding? = null
    private val binding get() = _binding!!
    private val viewModel by lazy {
        ViewModelProvider(this)[AuthViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        signIn()
        signUp()
    }

    private fun signUp() {
        binding.signUpTv.setOnClickListener {
            findNavController().navigate(AuthFragmentDirections.actionAuthFragmentToSetNameSurnameFragment())
        }
    }

    private fun signIn() {
        with(binding) {
            val email = emailTfEt.text.toString()
            val password = passwordTfEt.text.toString()
            signInBtn.setOnClickListener {

                viewModel.login(email, password).observe(viewLifecycleOwner) {
                    if (email.isNotBlank() && password.isNotBlank()) {
                        viewModel.login(email, password)
                        findNavController().navigate(R.id.action_authFragment_to_profileFragment2)
                    } else {
                        Toast.makeText(
                            requireContext(),
                            getString(R.string.fill_in_the_fields),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}