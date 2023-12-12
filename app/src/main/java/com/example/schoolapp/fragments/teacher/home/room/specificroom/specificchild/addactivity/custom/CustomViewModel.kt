package com.example.schoolapp.fragments.teacher.home.room.specificroom.specificchild.addactivity.custom

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.models.AddActivity
import com.example.domain.models.AdditionalFieldNap
import com.example.domain.models.Custom
import com.example.domain.usecase.teacher.TeacherManageAddActivityUseCase
import com.example.domain.usecase.teacher.TeacherManageCustomUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CustomViewModel @Inject constructor(
    private val teacherManageCustomUseCase: TeacherManageCustomUseCase,
    private val teacherManageAddActivityUseCase: TeacherManageAddActivityUseCase,
    application: Application
) : AndroidViewModel(application) {

    private val _progressData = MutableLiveData<Custom>()
    val progressData: LiveData<Custom> get() = _progressData

    private val _categoryData = MutableLiveData<Custom>()
    val categoryData: LiveData<Custom> get() = _categoryData

    private val _scaleData = MutableLiveData<Custom>()
    val scaleData: LiveData<Custom> get() = _scaleData

    private val _addCustomResponse = MutableLiveData<AddActivity>()
    val addCustomResponse: LiveData<AddActivity> get() = _addCustomResponse

    init {
        getCustomData()
    }

    private fun getCustomData() {
        viewModelScope.launch {
            try {
                _categoryData.value = teacherManageCustomUseCase.getCategoryData()
                _progressData.value = teacherManageCustomUseCase.getProgressData()
                _scaleData.value = teacherManageCustomUseCase.getScaleData()
            } catch (e: Exception) {
                Toast.makeText(getApplication(), e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun addCustom(
        activityType: String,
        childId: Int,
        activityDate: String,
        notes:String?,
        categoryId: Int?,
        progressId: Int?,
        scaleId: Int?
    ) {
        viewModelScope.launch {
            try {
                _addCustomResponse.value = teacherManageAddActivityUseCase.addCustomActivity(
                    activityType,
                    childId,
                    activityDate,
                    notes,
                    categoryId,
                    progressId,
                    scaleId
                )
            } catch (e: Exception) {
                Toast.makeText(getApplication(), e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}