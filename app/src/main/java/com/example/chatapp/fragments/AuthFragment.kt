package com.example.chatapp.fragments

import android.os.Bundle
import android.util.Log
import android.util.Log.DEBUG
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.ListFragment
import com.example.chatapp.R
import com.example.chatapp.databinding.FragmentAuthBinding
import com.example.chatapp.func.replaceFragment
import com.google.firebase.auth.FirebaseAuth

private const val TAG = "AuthFragment"
private const val ARG_PARAM2 = "param2"


class AuthFragment : Fragment() {

    private var _binding : FragmentAuthBinding? = null
    private val binding get() = _binding!!
    private val auth = FirebaseAuth.getInstance()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding?.signUpTv?.setOnClickListener {
            replaceFragment(RegFragment())
            Log.d(TAG, "Fragment success replaced")
        }

        binding.signInBtn.setOnClickListener {
            authentication()
        }
    }

    private fun authentication() {
        val email = binding.emailTfEt.text.toString()
        val password = binding.passwordTfEt.text.toString()

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(requireActivity()){
            if(it.isSuccessful) {
                Log.d(TAG, "signInWithEmail:success")
                val user = auth.currentUser
                auth.updateCurrentUser(user!!)
                replaceFragment(MessageListFragment())
                Log.d(TAG, "Fragment success replaced")
            } else {
                Log.w(TAG, "signInWithEmail:failure")
                Toast.makeText(requireContext(), "Authentication failed.",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}