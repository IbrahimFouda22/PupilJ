package com.pupilJ.jk.fragments.teacher.home.reminder

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pupilJ.domain.models.DeleteReminder
import com.pupilJ.domain.models.Reminder
import com.pupilJ.domain.usecase.teacher.TeacherManageReminderUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ReminderViewModel @Inject constructor(
    private val teacherManageReminderUseCase: TeacherManageReminderUseCase,
    application: Application
) :
    AndroidViewModel(application) {

    private val _reminders = MutableLiveData<List<Reminder>>()
    val reminders: LiveData<List<Reminder>> get() = _reminders

    private val _deleteReminder = MutableLiveData<DeleteReminder>()
    val deleteReminder: LiveData<DeleteReminder> get() = _deleteReminder

    private val _navigateToRoom = MutableLiveData(false)
    val navigateToRoom: LiveData<Boolean> get() = _navigateToRoom

    private val _showReminderMsg = MutableLiveData(false)
    val showReminderMsg: LiveData<Boolean> get() = _showReminderMsg

    init {
        getReminders()
    }

    fun getReminders() {
        viewModelScope.launch {
            try {
                _reminders.value = teacherManageReminderUseCase.getReminder()
            } catch (e: Exception) {
                Toast.makeText(getApplication(), e.message.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun deleteReminder(reminderId: Int) {
        viewModelScope.launch {
            try {
                _showReminderMsg.value = true
                _deleteReminder.value = teacherManageReminderUseCase.deleteReminder(reminderId)
            } catch (e: Exception) {
                Toast.makeText(getApplication(), e.message.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun navigateToRoom() {
        _navigateToRoom.value = true
    }

    fun navigateToRoomDone() {
        _navigateToRoom.value = false
    }

    fun showReminderMsg() {
        _showReminderMsg.value = false
    }

}