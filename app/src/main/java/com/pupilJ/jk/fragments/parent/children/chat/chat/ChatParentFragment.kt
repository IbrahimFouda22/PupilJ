package com.pupilJ.jk.fragments.parent.children.chat.chat

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.pupilJ.domain.models.Message
import com.pupilJ.jk.databinding.FragmentChatParentBinding
import com.pupilJ.jk.util.back
import com.pusher.client.Pusher
import com.pusher.client.PusherOptions
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

@AndroidEntryPoint
class ChatParentFragment : Fragment() {
    private val navArgs by navArgs<ChatParentFragmentArgs>()
    private lateinit var binding: FragmentChatParentBinding
    private var size = 0
    private var chatId: String? = null
    private lateinit var pusher: Pusher
    @Inject
    lateinit var sharedPreferences: SharedPreferences
    @Inject
    lateinit var factory: ChatParentViewModel.AssistedFactory
    private val viewModel: ChatParentViewModel by viewModels {
        ChatParentViewModel.chatViewModelFactory(factory, navArgs.teacherId,navArgs.teacherName)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentChatParentBinding.inflate(layoutInflater)
        chatId = "user_${navArgs.teacherId}_user_${sharedPreferences.getInt("id", 0)}"
        binding.viewModel = viewModel
        binding.btnArrowBackChatParent.setOnClickListener {
            back(findNavController())
        }
        viewModel.startRoom.observe(viewLifecycleOwner) {
            viewModel.getMessages(it.chatId)
        }

        val adapter = ChatParentAdapter(sharedPreferences.getInt("id", 0))
        binding.recyclerChatParent.adapter = adapter
        viewModel.messages.observe(viewLifecycleOwner) {
            size = it.size
            adapter.setMessages(it.reversed())
            binding.recyclerChatParent.scrollToPosition(size - 1)

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
                binding.recyclerChatParent.scrollToPosition(size - 1)
            }
        }

        return binding.root
    }

}