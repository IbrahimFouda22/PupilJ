package com.example.schoolapp.fragments.teacher.home.room.specificroom.specificchild.addactivity.nap

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.models.AddActivity
import com.example.domain.models.AdditionalFieldNap
import com.example.domain.usecase.teacher.TeacherManageAddActivityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NapViewModel @Inject constructor(
    private val teacherManageAddActivityUseCase: TeacherManageAddActivityUseCase,
    application: Application
) : AndroidViewModel(application) {


    private val _addNapResponse = MutableLiveData<AddActivity>()
    val addNapResponse: LiveData<AddActivity> get() = _addNapResponse

    fun addNap(
        activityType: String,
        additionalFieldNap: AdditionalFieldNap,
        childId: Int,
        activityDate: String,
        notes:String?
    ) {
        viewModelScope.launch {
            try {
                _addNapResponse.value = teacherManageAddActivityUseCase.addNapActivity(
                    activityType, additionalFieldNap,childId, activityDate,notes
                )
            } catch (e: Exception) {
                Toast.makeText(getApplication(), e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

}