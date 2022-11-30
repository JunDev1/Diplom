package com.example.chatapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.example.chatapp.databinding.FragmentProfileBinding
import com.example.chatapp.func.replaceFragment
import com.example.chatapp.viewmodels.ProfileViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val TAG = "ProfileFragment"

class MyLifecycleObserver (private val registry: ActivityResultRegistry)
    : DefaultLifecycleObserver {
        private lateinit var getContent: ActivityResultLauncher<String>

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        getContent = registry.register("key", owner, ActivityResultContracts.GetContent()) {
        return@register
        }
    }
    fun selectImage() {
        getContent.launch("/image*")
    }
    }

open class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var observer: MyLifecycleObserver
    //private val dbRef = FirebaseDatabase.getInstance().getReference("Users")

    private val viewModel : ProfileViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observer = MyLifecycleObserver(requireActivity().activityResultRegistry)
        lifecycle.addObserver(observer)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val selectBtn = binding.profileIV
        val username = binding.usernameTV


        //gettingDataFromDatabase()

        username.text = viewModel.gettingDataFromDB().toString()

        selectBtn.setOnClickListener {
            observer.selectImage()
        }
        binding.exitTV.setOnClickListener {
            Firebase.auth.signOut().apply {
                replaceFragment(AuthFragment())
            }
        }

    }

//    fun gettingDataFromDatabase() {
//        val username = binding.usernameTV
//        username.text = viewModel.gettingDataFromDB().toString()
//    }
}

