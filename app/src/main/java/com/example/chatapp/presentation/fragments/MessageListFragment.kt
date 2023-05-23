package com.example.chatapp.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.databinding.FragmentMessageListBinding
import com.example.chatapp.domain.model.User
import com.example.chatapp.presentation.adapter.MessageListAdapter
import com.google.firebase.database.*

private const val TAG = "MessageListFragment"

class MessageListFragment : Fragment() {
    private lateinit var messageListAdapter: MessageListAdapter
    private lateinit var messageListRecyclerView: RecyclerView
    private lateinit var databaseReference: DatabaseReference
    private lateinit var valueEventListener: ValueEventListener

    private var _binding: FragmentMessageListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMessageListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupFirebase()
        loadMessageList()
    }

    private fun setupRecyclerView() {
        messageListAdapter = MessageListAdapter()
        binding.messageListRowRV.adapter = messageListAdapter
        binding.messageListRowRV.layoutManager = LinearLayoutManager(requireContext())
    }
    private fun setupFirebase() {
        // Получите ссылку на вашу ветку в FirebaseDatabase
        databaseReference = FirebaseDatabase.getInstance().getReference("users")

        valueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val userList = ArrayList<User>()
                for (snapshot in dataSnapshot.children) {
                    val user = snapshot.getValue(User::class.java)
                    if (user != null) {
                        userList.add(user)
                    }
                }
                messageListAdapter.setUsers(userList)
                Log.d(TAG, dataSnapshot.value.toString())
            }
            override fun onCancelled(databaseError: DatabaseError) {
                Log.e(TAG, databaseError.message)
                // Обработка ошибок при чтении из FirebaseDatabase
            }
        }
    }
    private fun loadMessageList() {
        val messageList = mutableListOf<User>()
        databaseReference.addValueEventListener(valueEventListener)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}