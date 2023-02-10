package com.example.chatapp.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.chatapp.databinding.FragmentRegBinding
import com.example.chatapp.domain.User
import com.example.chatapp.func.replaceFragment
import com.example.chatapp.presentation.activities.MainActivity
import com.example.chatapp.presentation.viewmodels.RegViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


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

            Log.d(TAG, "Back to auth fragment")
        }
    }

    private fun launchRegistration() {
        binding.signUpBtn.setOnClickListener {
            registration()
        }
    }

    private fun registration() {
        val email = binding.emailTfEt.toString()
        val password = binding.passwordTfEt.toString()
        viewModel.registrationFirebase(email, password)
    }

//    private fun writeNewUser() {
//        val uid = dbRef.push().key!!
//        val email = binding.emailTfEt.text.toString()
//        val name = binding.nameTfEt.text.toString()
//        val surname = binding.surnameTfEt.text.toString()
//
//        val writeUser = User(name, surname, email, uid)
//
//        dbRef.child(uid).setValue(writeUser)
//    }

//    private fun registration() {
//        val email = binding.emailTfEt.text.toString()
//        val password = binding.passwordTfEt.text.toString()
//        val confirmPassword = binding.confirmPasswordTfEt.text.toString()
//
//        if (email.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
//            Toast.makeText(requireContext(), "Fields can't be empty", Toast.LENGTH_SHORT).show()
//        }
//
//        if (password != confirmPassword) {
//            Toast.makeText(requireContext(), "Passwords don't match", Toast.LENGTH_SHORT).show()
//        }
//
//        auth.createUserWithEmailAndPassword(email, password)
//            .addOnCompleteListener(requireActivity()) {
//                if (it.isSuccessful) {
//                    Log.d(TAG, "Success registration new user")
//                    val user = auth.currentUser
//                    auth.updateCurrentUser(user!!)
//                    replaceFragment(ProfileFragment())
//                } else {
//                    Log.e(TAG, "createUserWithEmail:failure", it.exception)
//                    Toast.makeText(requireContext(), "Registration failed", Toast.LENGTH_SHORT)
//                        .show()
//                }
//            }
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}