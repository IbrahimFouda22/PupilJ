package com.pupilJ.jk.fragments.teacher.setting

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingTeacherViewModel @Inject constructor(application: Application) :AndroidViewModel(application) {

    private val _navigateToAboutUs = MutableLiveData(false)
    val navigateToAboutUs : LiveData<Boolean> get() = _navigateToAboutUs

    private val _navigateToContactUs = MutableLiveData(false)
    val navigateToContactUs : LiveData<Boolean> get() = _navigateToContactUs

    private val _navigateToLanguage = MutableLiveData(false)
    val navigateToLanguage : LiveData<Boolean> get() = _navigateToLanguage

    fun navigateToAboutUs(){
        _navigateToAboutUs.value = true
    }
    fun navigateToAboutUsDone(){
        _navigateToAboutUs.value = false
    }

    fun navigateToContactUs(){
        _navigateToContactUs.value = true
    }
    fun navigateToContactUsDone(){
        _navigateToContactUs.value = false
    }

    fun navigateToLanguage(){
        _navigateToLanguage.value = true
    }
    fun navigateToLanguageDone(){
        _navigateToLanguage.value = false
    }
}