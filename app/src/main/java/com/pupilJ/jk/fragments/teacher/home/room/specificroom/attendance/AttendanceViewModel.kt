package com.pupilJ.jk.fragments.teacher.home.room.specificroom.attendance

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope

import com.pupilJ.domain.models.Attendance
import com.pupilJ.domain.models.ChildrenTeacher
import com.pupilJ.domain.models.NoInternetException
import com.pupilJ.domain.usecase.teacher.TeacherManageAttendanceUseCase
import com.pupilJ.domain.usecase.teacher.TeacherManageClasses
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

class AttendanceViewModel @AssistedInject constructor(
    @Assisted private val list: List<ChildrenTeacher>,
    private val teacherManageClasses: TeacherManageClasses,
    private val teacherManageAttendanceUseCase: TeacherManageAttendanceUseCase,
    application: Application
) : AndroidViewModel(application) {

    private val _children = MutableLiveData<List<ChildrenTeacher>>()
    val children: LiveData<List<ChildrenTeacher>> get() = _children

    private val _status = MutableLiveData<Attendance>()
    val status: LiveData<Attendance> get() = _status

    init {
        _children.value = list
    }


    fun setAttendance(
        attendType: String,
        attendDate: String,
        children: List<ChildrenTeacher>
    ) {
        viewModelScope.launch {
            try {
                _status.value = teacherManageAttendanceUseCase.setAttendance(attendType, attendDate, children)
            }catch (e :Exception){
                Toast.makeText(getApplication(), e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun getChildren(classId:Int) {
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

    @dagger.assisted.AssistedFactory
    interface AssistedFactory {
        fun create(list: List<ChildrenTeacher>): AttendanceViewModel
    }

    companion object {
        fun attendanceFactory(
            assistedFactory: AssistedFactory,
            list: List<ChildrenTeacher>
        ): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return assistedFactory.create(list) as T
                }
            }
        }
    }
}