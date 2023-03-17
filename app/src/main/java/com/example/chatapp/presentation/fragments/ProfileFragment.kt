package com.example.chatapp.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.chatapp.databinding.FragmentProfileBinding
import com.example.chatapp.presentation.viewmodels.ProfileViewModel
import com.google.firebase.auth.FirebaseAuth

private const val TAG = "ProfileFragment"

open class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            val nickname = nicknameTV.toString()
            val exitInfo = exitTV
            val email = emailTV.toString()
            val photo = profileIV.toString()
            viewModel.getEmail(email).observe(viewLifecycleOwner) {
                return@observe
            }
            viewModel.getNickname(nickname).observe(viewLifecycleOwner) {
                return@observe
            }
            viewModel.getPhoto(photo).observe(viewLifecycleOwner) {
                return@observe
            }
            exitInfo.setOnClickListener {
                //todo exit
            }
        }
    }

    override fun onStart() {
        super.onStart()
        //запустить наблюдателя
    }

    override fun onDestroy() {
        super.onDestroy()
        //удалить наблюдателя
    }

}

