package com.example.schoolapp.fragments.teacher.home.room.specificroom

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.domain.models.ChildrenTeacher
import com.example.domain.models.Classes
import com.example.domain.models.NoInternetException
import com.example.domain.usecase.teacher.TeacherManageClasses
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

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
