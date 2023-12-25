package com.pupilJ.jk.fragments.teacher.home.message.chat

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.pupilJ.domain.models.Message
import com.pupilJ.domain.models.StartRoom
import com.pupilJ.domain.usecase.teacher.TeacherManageMessagesUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch


class ChatViewModel @AssistedInject constructor(
    @Assisted private val userId:Int,
    @Assisted private val parentName: String,
    private val teacherManageMessagesUseCase: TeacherManageMessagesUseCase,
    application: Application
) : AndroidViewModel(application) {


    private val _startRoom = MutableLiveData<StartRoom>()
    val startRoom: LiveData<StartRoom>
        get() = _startRoom

    private val _messages = MutableLiveData<List<Message>>()
    val messages: LiveData<List<Message>>
        get() = _messages

    private val _sendMessage = MutableLiveData<Message>()

    val name = MutableLiveData(parentName)

    init {
        startRoom(userId)
    }

    private fun startRoom(userId: Int){
        viewModelScope.launch {
            try {
                _startRoom.value = teacherManageMessagesUseCase.startRoom(userId)
            }catch (e:Exception){
                Toast.makeText(getApplication(), e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun getMessages(chatId:String){
        viewModelScope.launch {
            try {
                _messages.value = teacherManageMessagesUseCase.getMessages(chatId)
            }catch (e:Exception){
                Toast.makeText(getApplication(), e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun sendMessage(msg:String?,chatId:String){
        viewModelScope.launch {
            try {
                _sendMessage.value = teacherManageMessagesUseCase.senMessage(msg, chatId)
            }catch (e:Exception){
                Toast.makeText(getApplication(), e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
    @dagger.assisted.AssistedFactory
    interface AssistedFactory {
        fun create(userId: Int,parentName:String): ChatViewModel
    }

    companion object {
        fun chatViewModelFactory(
            assistedFactory: AssistedFactory,
            userId: Int,
            parentName:String
        ): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return assistedFactory.create(userId,parentName) as T
                }
            }
        }
    }
}