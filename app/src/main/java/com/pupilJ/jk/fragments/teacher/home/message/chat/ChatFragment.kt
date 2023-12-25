package com.pupilJ.jk.fragments.teacher.home.message.chat

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.pupilJ.domain.models.Message
import com.pupilJ.jk.databinding.FragmentChatBinding
import com.pupilJ.jk.util.back
import com.pusher.client.Pusher
import com.pusher.client.PusherOptions
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

@AndroidEntryPoint
class ChatFragment : Fragment() {

    private val navArgs by navArgs<ChatFragmentArgs>()
    private lateinit var binding: FragmentChatBinding
    private var size = 0
    private var chatId: String? = null
    private lateinit var pusher: Pusher
    @Inject
    lateinit var sharedPreferences: SharedPreferences
    @Inject
    lateinit var factory: ChatViewModel.AssistedFactory
    private val viewModel: ChatViewModel by viewModels {
        ChatViewModel.chatViewModelFactory(factory, navArgs.parentId,navArgs.parentName)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentChatBinding.inflate(layoutInflater)
        chatId = "user_${sharedPreferences.getInt("id", 0)}_user_${navArgs.parentId}"
        binding.viewModel = viewModel
        binding.btnArrowBackChat.setOnClickListener {
            back(findNavController())
        }
        viewModel.startRoom.observe(viewLifecycleOwner) {
            chatId = it.chatId
            viewModel.getMessages(it.chatId)
        }

        val adapter = ChatAdapter(sharedPreferences.getInt("id", 0))
        binding.recyclerChat.adapter = adapter
        viewModel.messages.observe(viewLifecycleOwner) {
            size = it.size
            adapter.setMessages(it.reversed())
            binding.recyclerChat.scrollToPosition(size - 1)

        }

        binding.btnSendMessage.setOnClickListener {
            if (!chatId.isNullOrEmpty()) {
                viewModel.sendMessage(binding.edtSendMessage.text.toString(), chatId!!)
                binding.edtSendMessage.setText("")
            }
        }

        val options = PusherOptions().setCluster("mt1")
        pusher = Pusher("ee0cd9e85a343e6fc65f", options)

        pusher.connect()
        val channel = pusher.subscribe(chatId)
        channel.bind("send_message_event") { data ->
            val jsonObject = JSONObject(data.data)
            val message = jsonObject.getJSONObject("msg")
            val m = Message(
                message.getInt("from"),
                message.getString("message"),
            )
            lifecycleScope.launch {
                adapter.addMessage(m)
                ++size
                binding.recyclerChat.scrollToPosition(size - 1)
            }
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        pusher.disconnect()
    }

}