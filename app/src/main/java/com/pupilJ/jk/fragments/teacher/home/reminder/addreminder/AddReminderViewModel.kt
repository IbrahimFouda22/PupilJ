package com.pupilJ.jk.fragments.teacher.home.reminder.addreminder

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pupilJ.domain.models.AddReminder
import com.pupilJ.domain.models.EmptyDataException
import com.pupilJ.domain.models.InvalidData
import com.pupilJ.domain.models.NoInternetException
import com.pupilJ.domain.usecase.teacher.TeacherManageReminderUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class AddReminderViewModel @Inject constructor(
    private val teacherManageReminderUseCase: TeacherManageReminderUseCase,
    application: Application
) : AndroidViewModel(
    application
) {
    private val _addReminderResponse = MutableLiveData<AddReminder>()
    val addReminderResponse: LiveData<AddReminder> get() = _addReminderResponse

    fun addReminder(
        activityType: String?,
        childId: Int,
        activityDate: String,
        repeatId: Int,
    ) {
        viewModelScope.launch {
            try {
                _addReminderResponse.value = teacherManageReminderUseCase.addReminder(
                    activityType, activityDate,childId,repeatId
                )
            } catch (e: Exception) {
                Toast.makeText(getApplication(), e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}