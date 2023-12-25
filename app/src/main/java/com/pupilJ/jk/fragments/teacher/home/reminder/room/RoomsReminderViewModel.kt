package com.pupilJ.jk.fragments.teacher.home.reminder.room

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
class RoomsReminderViewModel @Inject constructor(
    private val teacherManageClasses: TeacherManageClasses,
    //private val sharedPreferences: SharedPreferences,
    application: Application
) :
    AndroidViewModel(application) {
    private val _rooms = MutableLiveData<List<Classes>>()
    val rooms: LiveData<List<Classes>>
        get() = _rooms

    private val _navigateToChildren = MutableLiveData(false)
    val navigateToChildren: LiveData<Boolean>
        get() = _navigateToChildren
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

    fun navigateToChildren(){
        _navigateToChildren.value = true
    }
    fun navigateToChildrenDone(){
        _navigateToChildren.value = false
    }

}