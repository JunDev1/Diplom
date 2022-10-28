package com.example.chatapp.fragments

import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.chatapp.R
import com.example.chatapp.databinding.FragmentRegBinding
import com.example.chatapp.firebase.FirebaseHelper
import com.example.chatapp.func.replaceFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


private const val TAG = "RegFragment"
private const val ARG_PARAM2 = "param2"


class RegFragment : Fragment() {

    private var _binding: FragmentRegBinding? = null
    private val binding get() = _binding!!
    private val reg = Firebase.auth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentRegBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signUpBtn.setOnClickListener {
            registration()
        }

        binding.backToAuthTv.setOnClickListener {
            replaceFragment(AuthFragment())
            Log.d(TAG, "Back to auth fragment")
        }
    }

    private fun registration() {
        val email = binding.emailTfEt.text.toString()
        val password = binding.passwordTfEt.text.toString()
        val confirmPassword = binding.confirmPasswordTfEt.text.toString()

        if (email.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
            Toast.makeText(requireContext(), "Fields can't be empty", Toast.LENGTH_SHORT).show()
        }

        if (password != confirmPassword) {
            Toast.makeText(requireContext(), "Passwords don't match", Toast.LENGTH_SHORT).show()
        }

        reg.createUserWithEmailAndPassword(email, password).addOnCompleteListener(requireActivity()){
            if (it.isSuccessful){
                Log.d(TAG, "Success registration new user")
                val user = reg.currentUser
                reg.updateCurrentUser(user!!)
                replaceFragment(MessageListFragment())
            } else {
                Log.w(TAG, "createUserWithEmail:failure")
                Toast.makeText(requireContext(),"Registration failed", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    companion object {
//        val firebaseelp = FirebaseHelper
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}