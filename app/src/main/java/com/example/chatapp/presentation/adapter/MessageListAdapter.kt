package com.example.chatapp.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.chatapp.R
import com.example.chatapp.databinding.MessageRowBinding
import com.example.chatapp.domain.model.User
import com.example.chatapp.presentation.fragments.MessageListFragmentDirections
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage

private const val TAG = "MessageListAdapter"

class MessageListAdapter(
    private val navController: NavController
) : RecyclerView.Adapter<MessageListAdapter.MessageListViewHolder>() {
    private val users = ArrayList<User>()
//    private val action = navController.navigate(MessageListFragmentDirections.actionMessageListFragmentToChatWindowFragment())


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageListViewHolder {
        val binding = MessageRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MessageListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MessageListViewHolder, position: Int) {
        val currentUser = users[position]
        holder.bind(currentUser)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    fun setUsers(userList: List<User>) {
        users.clear()
        users.addAll(userList)
        notifyDataSetChanged()
    }

    inner class MessageListViewHolder(val binding: MessageRowBinding) : RecyclerView.ViewHolder(binding.root) {
        private val cardView = binding.itemCv
        private val nickname = binding.tvMessageRowName
        private val photoProfile = binding.iconMessageRow

        init {
            binding.itemCv.setOnClickListener {

                // Создание действия навигации с передачей необходимых данных
                val action = MessageListFragmentDirections.actionMessageListFragmentToChatWindowFragment()

                // Навигация к указанному действию
                navController.navigate(action)
            }
        }

        fun bind(user: User) {
            val storageRef = FirebaseStorage.getInstance().reference
            val uid = FirebaseAuth.getInstance().uid
            val photoRef = storageRef.child("images/${uid}/${uid}.jpg")
            nickname.text = user.nickname
            // Загрузка фото пользователя в photoProfileImageView с помощью Glide или другой библиотеки
            // Glide.with(photoProfileImageView).load(user.photoUrl).into(photoProfileImageView)
            photoRef.downloadUrl.addOnSuccessListener { uri ->
                Log.d(TAG, uri.toString())
                Glide.with(photoProfile).load(user.photoImageUrl).diskCacheStrategy(
                    DiskCacheStrategy.ALL
                ).placeholder(R.drawable.ic_baseline_account_circle_24)
                    .into(photoProfile)
            }.addOnFailureListener { exception ->
                Log.e(TAG, "Failed to get photo URL from FirebaseStorage", exception)
                Log.e(TAG, user.userId.toString())
            }
        }
    }
}
