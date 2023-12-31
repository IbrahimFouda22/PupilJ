package com.pupilJ.jk.fragments.teacher.home.room.specificroom

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope

import com.pupilJ.domain.models.ChildrenTeacher
import com.pupilJ.domain.models.NoInternetException
import com.pupilJ.domain.usecase.teacher.TeacherManageClasses
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

class SpecificRoomViewModel @AssistedInject constructor(
    private val teacherManageClasses: TeacherManageClasses,
    @Assisted private val classId:Int,
    @Assisted private val roomName:String,
    application: Application
) :
    AndroidViewModel(application) {

    private val _children = MutableLiveData<List<ChildrenTeacher>>()
    val children: LiveData<List<ChildrenTeacher>>
        get() = _children

    private val _navigateToAttendance = MutableLiveData(false)
    val navigateToAttendance: LiveData<Boolean>
        get() = _navigateToAttendance

    private val _navigateToChat = MutableLiveData(false)
    val navigateToChat: LiveData<Boolean>
        get() = _navigateToChat

    val roomNumber = MutableLiveData<String>()


    init {
        setRoom(roomName)
        getChildren(classId)
    }

    private fun getChildren(classId:Int) {
        viewModelScope.launch {
            try {
                _children.value = teacherManageClasses.getChildrenOfClasses(classId)
            } catch (e: NoInternetException) {
                Toast.makeText(getApplication(), e.message, Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(getApplication(), e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun setRoom(roomNum:String){
        roomNumber.value = roomNum
    }

    fun navigateToAttendance(){
        _navigateToAttendance.value = true
    }
    fun navigateToAttendanceDone(){
        _navigateToAttendance.value = false
    }

    fun navigateToChat(){
        _navigateToChat.value = true
    }
    fun navigateToChatDone(){
        _navigateToChat.value = false
    }

    @dagger.assisted.AssistedFactory
    interface AssistedFactory {
        fun create(id: Int,roomName: String): SpecificRoomViewModel
    }

    companion object {
        fun specificRoomViewModelFactory(
            assistedFactory: AssistedFactory,
            id: Int,
            roomName: String
        ): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return assistedFactory.create(id,roomName) as T
                }
            }
        }
    }
}
