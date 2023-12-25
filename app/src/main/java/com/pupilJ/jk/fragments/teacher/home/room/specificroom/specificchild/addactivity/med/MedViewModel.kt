package com.pupilJ.jk.fragments.teacher.home.room.specificroom.specificchild.addactivity.med

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pupilJ.domain.models.AddActivity
import com.pupilJ.domain.usecase.teacher.TeacherManageAddActivityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MedViewModel @Inject constructor(
    private val teacherManageAddActivityUseCase: TeacherManageAddActivityUseCase,
    application: Application
) : AndroidViewModel(application) {


    private val _addMedResponse = MutableLiveData<AddActivity>()
    val addMedResponse: LiveData<AddActivity> get() = _addMedResponse

    fun addMed(
        activityType: String,
        childId: Int,
        activityDate: String,
        notes:String?
    ) {
        viewModelScope.launch {
            try {
                _addMedResponse.value = teacherManageAddActivityUseCase.addNoteMedIncidentActivity(
                    activityType,childId, activityDate,notes
                )
            } catch (e: Exception) {
                Toast.makeText(getApplication(), e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

}