package com.pupilJ.jk.fragments.teacher.home.message.parent

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.pupilJ.domain.models.NoInternetException
import com.pupilJ.domain.models.Parent
import com.pupilJ.domain.usecase.teacher.TeacherManageClasses
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

class ParentsViewModel @AssistedInject constructor(
    private val teacherManageClasses: TeacherManageClasses,
    @Assisted private val classId:Int,
    @Assisted private val roomName:String,
    application: Application
) :
    AndroidViewModel(application) {
    private val _parents = MutableLiveData<List<Parent>>()
    val parents: LiveData<List<Parent>>
        get() = _parents

    val roomNumber = MutableLiveData<String>()

    init {
        setRoom(roomName)
        getParents()
    }

    private fun getParents() {
        viewModelScope.launch {
            try {
                _parents.value = teacherManageClasses.getParentsOfClass(classId)
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

    @dagger.assisted.AssistedFactory
    interface AssistedFactory {
        fun create(id: Int,roomName: String): ParentsViewModel
    }

    companion object {
        fun parentsViewModel(
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