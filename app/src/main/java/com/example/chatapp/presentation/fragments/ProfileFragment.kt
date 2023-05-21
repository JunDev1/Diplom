package com.example.chatapp.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.chatapp.R
import com.example.chatapp.databinding.FragmentProfileBinding
import com.example.chatapp.domain.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage

private const val TAG = "ProfileFragment"

class ProfileFragment : Fragment() {
    private val userUid = FirebaseAuth.getInstance().currentUser!!.uid
    private val database = FirebaseDatabase.getInstance().reference
    private val storageRef = FirebaseStorage.getInstance().reference
    private val photoRef = storageRef.child("images/$userUid/${userUid}.jpg")
    private val auth = FirebaseAuth.getInstance()
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
//    private val viewModel by lazy {
//        ViewModelProvider(this)[ProfileViewModel::class.java]
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        launchSignOut()
        profileInfo()
    }


    private fun profileInfo() {
        database.child("users").child(userUid)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        val user: User? = snapshot.getValue(User::class.java)
                        val nickname = user?.nickname
                        val email = user?.email
                        binding.nicknameTV.text = nickname
                        binding.emailTV.text = email
                        photoRef.downloadUrl.addOnSuccessListener { uri ->
                            Log.d(TAG, uri.toString())
                            Glide.with(requireContext()).load(uri).diskCacheStrategy(
                                DiskCacheStrategy.ALL
                            ).placeholder(R.drawable.ic_baseline_account_circle_24)
                                .into(binding.profileIV)
                        }.addOnFailureListener {exception ->
                            Log.e(TAG, "Failed to get photo URL from FirebaseStorage", exception)
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
    }


    private fun launchSignOut() {
        binding.exitTV.setOnClickListener {
            auth.signOut()
            findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToAuthFragment())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        private const val PERMISSION_REQUEST_CODE = 123
    }

}

