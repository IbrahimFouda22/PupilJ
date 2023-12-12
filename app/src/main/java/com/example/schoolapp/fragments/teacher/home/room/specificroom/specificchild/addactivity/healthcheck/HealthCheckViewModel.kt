package com.example.schoolapp.fragments.teacher.home.room.specificroom.specificchild.addactivity.healthcheck

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
class HealthCheckViewModel @Inject constructor(
    private val teacherManageAddActivityUseCase: TeacherManageAddActivityUseCase,
    application: Application
) : AndroidViewModel(application) {


    private val _addHealthCheckResponse = MutableLiveData<AddActivity>()
    val addHealthCheckResponse: LiveData<AddActivity> get() = _addHealthCheckResponse

    fun addHealthCheck(
        activityType: String,
        childId: Int,
        activityDate: String,
        notes:String?,
        temperature:String?
    ) {
        viewModelScope.launch {
            try {
                _addHealthCheckResponse.value = teacherManageAddActivityUseCase.addHealthCheckActivity(
                    activityType,childId, activityDate,notes,temperature
                )
            } catch (e: Exception) {
                Toast.makeText(getApplication(), e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

}