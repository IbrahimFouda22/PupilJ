package com.pupilJ.jk.fragments.teacher.home.student.room

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pupilJ.domain.models.Classes
import com.pupilJ.domain.models.NoInternetException
import com.pupilJ.domain.usecase.teacher.TeacherManageClasses
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomHomeTeacherViewModel @Inject constructor(
    private val teacherManageClasses: TeacherManageClasses,
    application: Application
) :
    AndroidViewModel(application) {

    private val _rooms = MutableLiveData<List<Classes>>()
    val rooms: LiveData<List<Classes>>
        get() = _rooms

    init {
        getRooms()
    }

    private fun getRooms() {
        viewModelScope.launch {
            try {
                _rooms.value = teacherManageClasses.getClasses()
            } catch (e: NoInternetException) {
                Toast.makeText(getApplication(), e.message, Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(getApplication(), e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
