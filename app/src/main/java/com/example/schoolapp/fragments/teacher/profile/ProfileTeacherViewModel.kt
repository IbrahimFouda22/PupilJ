package com.example.schoolapp.fragments.teacher.profile

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.models.UserProfile
import com.example.schoolapp.util.token
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileTeacherViewModel @Inject constructor(
    application: Application
) :
    AndroidViewModel(application) {
    private val _navigateToJoinAs = MutableLiveData(false)
    val navigateToJoinAs: LiveData<Boolean> get() = _navigateToJoinAs
     fun navigateToJoinAs(){
         _navigateToJoinAs.value = true
     }

    fun navigateToJoinAsDone(){
        _navigateToJoinAs.value = false
    }
}