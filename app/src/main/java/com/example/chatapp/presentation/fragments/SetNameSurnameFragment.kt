package com.example.chatapp.presentation.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.chatapp.R
import com.example.chatapp.databinding.FragmentSetNameSurnameBinding
import com.example.chatapp.presentation.viewmodels.SetNameSurnameViewModel

class SetNameSurnameFragment : Fragment() {

    companion object {
        fun newInstance() = SetNameSurnameFragment()
    }

    private var _binding: FragmentSetNameSurnameBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: SetNameSurnameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSetNameSurnameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[SetNameSurnameViewModel::class.java]
        binding.btnNextStep.setOnClickListener {
            findNavController().navigate(SetNameSurnameFragmentDirections.actionSetNameSurnameFragmentToRegFragment())
        }
    }
}