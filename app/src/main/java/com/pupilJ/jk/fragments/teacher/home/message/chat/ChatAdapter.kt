package com.pupilJ.jk.fragments.teacher.home.message.chat

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pupilJ.domain.models.Message
import com.pupilJ.jk.databinding.LayoutMessageReceivedBinding
import com.pupilJ.jk.databinding.LayoutMessageSentBinding

class ChatAdapter(private val userId:Int) : RecyclerView.Adapter<ChatAdapter.MessageViewHolder>() {

    private val messages: ArrayList<Message> = arrayListOf()


    @SuppressLint("NotifyDataSetChanged")
    fun setMessages(message: List<Message>){
        message.toCollection(messages)
        notifyDataSetChanged()
    }
    @SuppressLint("NotifyDataSetChanged")
    fun addMessage(message: Message){
        messages.add(message)
        notifyDataSetChanged()
    }
    open class MessageViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        open fun bind(msg:Message) {}
    }
    inner class MessageViewHolderReceived(private val layoutMessageReceivedBinding: LayoutMessageReceivedBinding) :
        MessageViewHolder(layoutMessageReceivedBinding.root) {
            override fun bind(msg:Message){
                layoutMessageReceivedBinding.message = msg
            }
    }
    class MessageViewHolderSent(private val layoutMessageSentBinding: LayoutMessageSentBinding) :
        MessageViewHolder(layoutMessageSentBinding.root) {
        override fun bind(msg:Message){
            layoutMessageSentBinding.message = msg
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        return if(viewType == RECEIVED) {
            val inflater = LayoutInflater.from(parent.context)
            MessageViewHolderReceived(
                LayoutMessageReceivedBinding.inflate(inflater,parent,false)
            )

        } else {
            val inflater = LayoutInflater.from(parent.context)
            MessageViewHolderSent(
                LayoutMessageSentBinding.inflate(inflater,parent,false)
            )

        }
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message = messages[position]
        holder.bind(message)
    }


    override fun getItemViewType(position: Int): Int {
        val message = messages[position]

        return if(userId == message.userId) {
            SENT
        }
        else {
            RECEIVED

        }
    }

    override fun getItemCount(): Int {
        return messages.size
    }


    companion object{
        const val RECEIVED = 1
        const val SENT = 2
    }
}