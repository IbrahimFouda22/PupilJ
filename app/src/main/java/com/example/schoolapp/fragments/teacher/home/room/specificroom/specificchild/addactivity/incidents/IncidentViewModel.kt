package com.example.schoolapp.fragments.teacher.home.room.specificroom.specificchild.addactivity.incidents

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.models.AddActivity
import com.example.domain.usecase.teacher.TeacherManageAddActivityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IncidentViewModel @Inject constructor(
    private val teacherManageAddActivityUseCase: TeacherManageAddActivityUseCase,
    application: Application
) : AndroidViewModel(application) {


    private val _addIncidentResponse = MutableLiveData<AddActivity>()
    val addIncidentResponse: LiveData<AddActivity> get() = _addIncidentResponse

    fun addIncident(
        activityType: String,
        childId: Int,
        activityDate: String,
        notes:String?
    ) {
        viewModelScope.launch {
            try {
                _addIncidentResponse.value = teacherManageAddActivityUseCase.addNoteMedIncidentActivity(
                    activityType,childId, activityDate,notes
                )
            } catch (e: Exception) {
                Toast.makeText(getApplication(), e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

}