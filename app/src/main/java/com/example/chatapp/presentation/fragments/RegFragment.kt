package com.example.chatapp.presentation.fragments

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.chatapp.R
import com.example.chatapp.databinding.FragmentRegBinding
import com.example.chatapp.domain.model.User
import com.example.chatapp.presentation.viewmodels.RegViewModel
import com.example.chatapp.presentation.viewmodels.ViewModelFactory
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage


private const val TAG = "RegFragment"

class RegFragment : Fragment() {
    private var selectedPhotoUri: Uri? = null
    private val storageRef = FirebaseStorage.getInstance().reference
    private val databaseReference = FirebaseDatabase.getInstance().reference
    private val auth = FirebaseAuth.getInstance()

    private var _binding: FragmentRegBinding? = null
    private val binding get() = _binding!!
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
        binding.profileIv.setOnClickListener {
            pickImage()
        }
    }

    private fun pickImage() {
        ImagePicker.with(this).galleryOnly().start()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            if (requestCode == ImagePicker.REQUEST_CODE) {
                val uri: Uri? = data?.data
                binding.profileIv.setImageURI(uri)
                selectedPhotoUri = uri
                Log.d(TAG, "${selectedPhotoUri.toString()}")
            }
        }
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

    private fun createUser(
        auth: FirebaseAuth,
        nickname: String,
        email: String,
        password: String,
    ) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                val currentUser = auth.currentUser
                val userUid = currentUser?.uid
                val email = binding.emailTfEt.text.toString()
                storageRef.putFile(selectedPhotoUri!!)
                if (userUid != null) {
                    val user = User(
                        nickname,
                        email,
                        photoImageUrl = selectedPhotoUri.toString()
                    )
                    val userRef = databaseReference.child("users").child(userUid)
                    userRef.setValue(user).addOnCompleteListener {
                        if (it.isSuccessful) {
                            selectedPhotoUri?.let {
                                val photoRef = storageRef.child("images/$userUid/${userUid}.jpg")
                                photoRef.putFile(it).addOnSuccessListener {
                                    it.storage.downloadUrl.addOnSuccessListener { uri ->
                                        val photoImageUrl = uri.toString()
                                        // Обновление ссылки на фотографию в базе данных
                                        userRef.child("photoImageUrl").setValue(photoImageUrl)
                                        Log.d(TAG, uri.toString())
                                    }.addOnFailureListener { e ->
                                        Log.e(TAG, "Failed to get download URL", e)
                                        // Обработка ошибки при получении URL загруженной фотографии
                                    }
                                }.addOnFailureListener { e ->
                                    Log.e(TAG, "Failed to upload photo to Firebase Storage", e)
                                    // Обработка ошибки при загрузке фотографии в Firebase Storage
                                }
                            } ?: run {
                                findNavController().navigate(RegFragmentDirections.actionRegFragmentToProfileFragment2())
                            }
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}