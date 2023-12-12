package com.example.schoolapp.fragments.teacher.home.room.specificroom.specificchild.addactivity.achievement

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
class AchievementViewModel @Inject constructor(
    private val teacherManageAddActivityUseCase: TeacherManageAddActivityUseCase,
    application: Application
) : AndroidViewModel(application) {


    private val _addAchievementResponse = MutableLiveData<AddActivity>()
    val addAchievementResponse: LiveData<AddActivity> get() = _addAchievementResponse

    fun addAchievement(
        activityType: String,
        childId: Int,
        activityDate: String,
        notes:String?,
        image:String?
    ) {
        viewModelScope.launch {
            try {
                _addAchievementResponse.value = teacherManageAddActivityUseCase.addAchievementActivity(
                    activityType,childId, activityDate,notes,image
                )
            } catch (e: Exception) {
                Toast.makeText(getApplication(), e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

}