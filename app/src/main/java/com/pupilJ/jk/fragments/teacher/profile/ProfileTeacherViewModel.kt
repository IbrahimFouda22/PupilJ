package com.pupilJ.jk.fragments.teacher.profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
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