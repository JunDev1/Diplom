package com.example.chatapp.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.chatapp.R
import com.example.chatapp.domain.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage

private const val TAG = "MessageListAdapter"

class MessageListAdapter : RecyclerView.Adapter<MessageListAdapter.MessageListViewHolder>() {

    private val users = ArrayList<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageListViewHolder {
        val userView = LayoutInflater.from(parent.context).inflate(
            R.layout.message_row,
            parent,
            false
        )
        return MessageListViewHolder(userView)
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

    class MessageListViewHolder(userView: View) : RecyclerView.ViewHolder(userView) {
        //private val cardView = userView.findViewById<CardView>(R.id.message_row)
        private val nickname = userView.findViewById<TextView>(R.id.tv_message_row)
        private val photoProfile = userView.findViewById<ImageView>(R.id.icon_message_row)

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
