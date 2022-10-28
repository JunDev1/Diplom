package com.example.chatapp.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.models.MessageListRow

class MessageListRowAdapter(private val context: Context ,
                            private val MessageList : MutableList <MessageListRow>)
    : RecyclerView.Adapter<MessageListRowAdapter.MessageListRowViewHolder>(){


    class MessageListRowViewHolder(MessageListRowLayout : MessageListRowLayoutBinding){
        val binding = MessageList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageListRowViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: MessageListRowViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}