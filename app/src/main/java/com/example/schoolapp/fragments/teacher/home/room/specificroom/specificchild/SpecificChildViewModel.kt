package com.example.schoolapp.fragments.teacher.home.room.specificroom.specificchild

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.domain.models.ChildrenActivity
import com.example.domain.usecase.teacher.TeacherGetChildActivityUseCase
import com.example.schoolapp.R
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

class SpecificChildViewModel @AssistedInject constructor(
    private val teacherChildActivityUseCase: TeacherGetChildActivityUseCase,
    application: Application,
    @Assisted id: Int
) :
    AndroidViewModel(application) {

    private val _activityType = MutableLiveData<String>()
    val activityType:LiveData<String> get() = _activityType

    private val _navigateToAddActivity = MutableLiveData(false)
    val navigateToAddActivity: LiveData<Boolean> get() = _navigateToAddActivity

    private val _navigateToProfile = MutableLiveData(false)
    val navigateToProfile: LiveData<Boolean> get() = _navigateToProfile

    private val _childrenActivity = MutableLiveData<List<ChildrenActivity>>()
    val childrenActivity: LiveData<List<ChildrenActivity>> get() = _childrenActivity

    init {
        getChildrenActivity(id)
    }

    private fun getChildrenActivity(id: Int) {
        viewModelScope.launch {
            try {
                _childrenActivity.value = teacherChildActivityUseCase.getChildrenActivity(id)
            } catch (e: Exception) {
                Toast.makeText(getApplication(), e.message, Toast.LENGTH_SHORT).show()

            }
            //_childrenActivity.value = teacherChildActivityUseCase.getChildrenActivity(id)

        }
    }

    fun setActivityType(activityType:String) {
        _activityType.value = activityType
    }

    fun navigateToAddActivity() {
        _navigateToAddActivity.value = true
    }
    fun navigateToAddActivityDone() {
        _navigateToAddActivity.value = false
    }

    fun navigateToProfile() {
        _navigateToProfile.value = true
    }
    fun navigateToProfileDone() {
        _navigateToProfile.value = false
    }

    @dagger.assisted.AssistedFactory
    interface AssistedFactory {
        fun create(id: Int): SpecificChildViewModel
    }

    companion object {
        fun specificChildViewModelFactory(assistedFactory: AssistedFactory,id: Int): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return assistedFactory.create(id) as T
                }
            }
        }
    }

}