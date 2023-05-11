package com.example.chatapp.presentation.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.map
import androidx.navigation.fragment.findNavController
import com.example.chatapp.R
import com.example.chatapp.databinding.FragmentRegBinding
import com.example.chatapp.domain.model.User
import com.example.chatapp.presentation.viewmodels.RegViewModel
import com.example.chatapp.presentation.viewmodels.ViewModelFactory
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

private const val TAG = "RegFragment"

class RegFragment : Fragment() {
    private val databaseReference = FirebaseDatabase.getInstance().reference
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
        with(binding) {
            signUpBtn.setOnClickListener {
                val nickname = nicknameTfEt.text.toString()
                val email = emailTfEt.text.toString()
                val password = passwordTfEt.text.toString()
                if (nickname.isNotBlank() && email.isNotBlank() && password.isNotBlank()) {
                    confirmPassword()
                } else {
                    Toast.makeText(requireActivity(), R.string.fields_blank, Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
    }

    private fun confirmPassword() {
        with(binding) {
            val nickname = nicknameTfEt.text.toString()
            val email = emailTfEt.text.toString()
            val password = passwordTfEt.text.toString()
            val confirmPassword = confirmPasswordTfEt.text.toString()
            if (confirmPassword == password) {
                createUser(auth, nickname, email, password)
            } else {
                Toast.makeText(requireContext(), R.string.password_dont_match, Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun createUser(auth: FirebaseAuth, nickname: String, email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                val currentUser = auth.currentUser
                val userUid = currentUser?.uid
                val email = binding.emailTfEt.text.toString()

                if (userUid != null) {
                    val user = User(nickname, email)
                    val userRef = databaseReference.child("users").child(userUid)
                    userRef.setValue(user).addOnCompleteListener {
                        if (it.isSuccessful) {
                            findNavController().navigate(RegFragmentDirections.actionRegFragmentToProfileFragment2())
                        } else {
                            Log.e(
                                "RegFragment",
                                "Failed to write user data to Firebase",
                                it.exception
                            )
                        }
                    }
                } else {
                    Log.e(
                        "RegFragment",
                        "Failed to register user in Firebase Authentication",
                        it.exception
                    )
                }

            } else {
                when (it.exception) {
                    is FirebaseAuthUserCollisionException -> {
                        Toast.makeText(context, "Пользователь уже существует", Toast.LENGTH_LONG)
                            .show()
                    }
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
}