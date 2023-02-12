package com.example.chatapp.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.chatapp.databinding.FragmentSetNameSurnameBinding
import com.example.chatapp.presentation.viewmodels.SetNameSurnameViewModel
import com.example.chatapp.presentation.viewmodels.SetNameSurnameViewModelFactory

class SetNameSurnameFragment() : Fragment() {

    private var _binding: FragmentSetNameSurnameBinding? = null
    private val binding get() = _binding!!
    private val viewModel by lazy {
        ViewModelProvider(
            this,
            SetNameSurnameViewModelFactory(
                requireActivity().application
            )
        )[SetNameSurnameViewModel::class.java]
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSetNameSurnameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnNextStep.setOnClickListener {
            findNavController().navigate(SetNameSurnameFragmentDirections.actionSetNameSurnameFragmentToRegFragment())
        }
//        setUsernameAndSurname()
    }

    //todo uncommented
//    private fun setUsernameAndSurname() {
//        binding.btnNextStep.setOnClickListener {
//            viewModel.sendDataToFirebase(
//                binding.nameTfEt.text.toString(),
//                binding.surnameTfEt.text.toString()
//            )
//        }
//    }
}