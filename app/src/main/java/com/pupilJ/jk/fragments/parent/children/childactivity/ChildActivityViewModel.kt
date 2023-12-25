package com.pupilJ.jk.fragments.parent.children.childactivity

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.pupilJ.domain.models.ChildrenActivity
import com.pupilJ.domain.usecase.parent.ParentGetChildActivityUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

class ChildActivityViewModel @AssistedInject constructor(
    private val parentGetChildActivityUseCase: ParentGetChildActivityUseCase,
    application: Application,
    @Assisted id: Int,
) :
    AndroidViewModel(application) {

    private val _activityType = MutableLiveData<String>()
    val activityType:LiveData<String> get() = _activityType


    private val _navigateToTeacher = MutableLiveData(false)
    val navigateToTeacher : LiveData<Boolean> get() = _navigateToTeacher

    private val _childrenActivity = MutableLiveData<List<ChildrenActivity>>()
    val childrenActivity: LiveData<List<ChildrenActivity>> get() = _childrenActivity

    init {
        getChildrenActivity(id)
    }

    private fun getChildrenActivity(id: Int) {
        viewModelScope.launch {
            try {
                _childrenActivity.value = parentGetChildActivityUseCase.getChildrenActivity(id)
            } catch (e: Exception) {
                Toast.makeText(getApplication(), e.message, Toast.LENGTH_SHORT).show()

            }
            //_childrenActivity.value = teacherChildActivityUseCase.getChildrenActivity(id)

        }
    }

    fun setActivityType(activityType:String) {
        _activityType.value = activityType
    }

    fun setChildrenActivity(childrenActivity: List<ChildrenActivity>) {
        _childrenActivity.value = childrenActivity
    }
    fun navigateToTeacher(){
        _navigateToTeacher.value = true
    }
    fun navigateToTeacherDone(){
        _navigateToTeacher.value = false
    }

    @dagger.assisted.AssistedFactory
    interface AssistedFactory {
        fun create(id: Int): ChildActivityViewModel
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