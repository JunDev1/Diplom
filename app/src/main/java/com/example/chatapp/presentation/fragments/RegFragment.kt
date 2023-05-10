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
import com.example.chatapp.databinding.FragmentRegBinding
import com.example.chatapp.domain.model.User
import com.example.chatapp.presentation.viewmodels.RegViewModel
import com.example.chatapp.presentation.viewmodels.ViewModelFactory
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

private const val TAG = "RegFragment"

class RegFragment : Fragment() {
    private val databaseReference = FirebaseDatabase.getInstance().reference.child("users1")
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
                if (email.isNotBlank() && password.isNotBlank()) {
                    viewModel.createUser(auth, email, password)
                    databaseReference.addValueEventListener(
                        object : ValueEventListener {
                            override fun onDataChange(snapshot: DataSnapshot) {
                                val post: User? = snapshot.getValue(User::class.java)
                                post?.let {
                                    post.nickname = nickname
                                    post.email = email
                                    databaseReference.setValue(post)
                                }
                            }
                            override fun onCancelled(error: DatabaseError) {
                                Log.w(TAG, "loadPost:onCancelled", error.toException())
                            }
                        })
                    findNavController().navigate(RegFragmentDirections.actionRegFragmentToProfileFragment2())
                } else {
                    Toast.makeText(requireActivity(), "Ваши поля пустые", Toast.LENGTH_LONG).show()
                }
            }
        }

    }

//    private fun getCurrentUser() {
//        if (currentUser != null) {
//            val userRef = database.child("users").child(currentUser.uid)
//            val userData = HashMap<String, Any>()
//            userData["username"] = binding.nicknameTfEt.text.toString()
//            userRef.setValue(userData)
//                .addOnSuccessListener {
//                    Log.d("RegFragment", "User created")
//                }
//                .addOnFailureListener {
//                    Log.d("RegFragment", "User can't created")
//                }
//        }
//    }

//    private fun setUserName(user: FirebaseUser?, nickname: String) {
//        if (user != null) {
//            val profileUpdates =
//                UserProfileChangeRequest.Builder().setDisplayName(nickname).build()
//            user.updateProfile(profileUpdates).addOnCompleteListener {
//                if (it.isSuccessful) {
//                    Log.d("RegFragment", "Profile updates ${nickname.toString()}")
//                } else {
//                    Log.d("RegFragment", "Profile not updates")
//                }
//            }
//        }
//    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
    }
}