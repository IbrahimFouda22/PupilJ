package com.pupilJ.jk.fragments.teacher.home.room.specificroom.specificchild.addactivity.nametoface

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope

import com.pupilJ.domain.models.AddActivity
import com.pupilJ.domain.models.AdditionalFieldNameToFace
import com.pupilJ.domain.usecase.teacher.TeacherManageAddActivityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NameToFaceViewModel @Inject constructor(
    private val teacherManageAddActivityUseCase: TeacherManageAddActivityUseCase,
    application: Application
) : AndroidViewModel(application) {


    private val _addNameToFaceResponse = MutableLiveData<AddActivity>()
    val addNameToFaceResponse: LiveData<AddActivity> get() = _addNameToFaceResponse

    fun addNameToFace(
        activityType: String,
        additionalFieldNameToFace: AdditionalFieldNameToFace,
        childId: Int,
        activityDate: String,
        notes:String?,
        image:String?
    ) {
        viewModelScope.launch {
            try {
                _addNameToFaceResponse.value = teacherManageAddActivityUseCase.addNameToFaceActivity(
                    activityType, additionalFieldNameToFace,childId, activityDate,notes,image
                )
            } catch (e: Exception) {
                Toast.makeText(getApplication(), e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

}