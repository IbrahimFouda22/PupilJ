package com.pupilJ.jk.fragments.parent.children.chat.teacher

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.pupilJ.domain.models.NoInternetException
import com.pupilJ.domain.models.Teacher
import com.pupilJ.domain.usecase.parent.ParentChildrenUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

class TeachersViewModel @AssistedInject constructor(
    private val parentChildrenUseCase: ParentChildrenUseCase,
    @Assisted private val childId:Int,
    @Assisted private val childName:String,
    application: Application
) :
    AndroidViewModel(application) {
    private val _teachers = MutableLiveData<List<Teacher>>()
    val teachers: LiveData<List<Teacher>>
        get() = _teachers

    val cName = MutableLiveData(childName)

    init {
        getTeachers()
    }

    private fun getTeachers() {
        viewModelScope.launch {
            try {
                _teachers.value = parentChildrenUseCase.getTeachersOfChildren(childId)
            } catch (e: NoInternetException) {
                Toast.makeText(getApplication(), e.message, Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(getApplication(), e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    @dagger.assisted.AssistedFactory
    interface AssistedFactory {
        fun create(id: Int,roomName: String): TeachersViewModel
    }

    companion object {
        fun teachersViewModel(
            assistedFactory: AssistedFactory,
            id: Int,
            childName: String
        ): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return assistedFactory.create(id,childName) as T
                }
            }
        }
    }

}