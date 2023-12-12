package com.example.schoolapp.fragments.teacher.home.room.specificroom.specificchild.addactivity.observation

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.models.AddActivity
import com.example.domain.models.AdditionalFieldPotty
import com.example.domain.models.Custom
import com.example.domain.usecase.teacher.TeacherManageAddActivityUseCase
import com.example.domain.usecase.teacher.TeacherManageMileStonesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ObservationViewModel @Inject constructor(
    private val teacherManageMileStonesUseCase: TeacherManageMileStonesUseCase ,
    private val teacherManageAddActivityUseCase: TeacherManageAddActivityUseCase,
    application: Application
) : AndroidViewModel(application) {

    private val _mileStonesData = MutableLiveData<Custom>()
    val mileStonesData: LiveData<Custom> get() = _mileStonesData

    private val _addObservationResponse = MutableLiveData<AddActivity>()
    val addObservationResponse: LiveData<AddActivity> get() = _addObservationResponse

   init {
        getCustomData()
    }

    private fun getCustomData() {
        viewModelScope.launch {
            try {
                _mileStonesData.value = teacherManageMileStonesUseCase.getMileStone()

            } catch (e: Exception) {
                Toast.makeText(getApplication(), e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun addObservation(
        activityType: String,
        childId: Int,
        activityDate: String,
        notes:String?,
        mileStoneId: Int?,
        staffOnly: Int?,
    ) {
        viewModelScope.launch {
            try {
                _addObservationResponse.value = teacherManageAddActivityUseCase.addObservationActivity(
                    activityType,
                    childId,
                    activityDate,
                    notes,
                    mileStoneId,
                    staffOnly,
                )
            } catch (e: Exception) {
                Toast.makeText(getApplication(), e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}