package com.pupilJ.jk.fragments.joinas

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class JoinAsViewModel:ViewModel() {
    private val _navigateToParent = MutableLiveData(false)
    val navigateToParent : LiveData<Boolean> get() = _navigateToParent

    private val _navigateToTeacher = MutableLiveData(false)
    val navigateToTeacher : LiveData<Boolean> get() = _navigateToTeacher

    fun navigateToParent(boolean: Boolean)
    {
        _navigateToParent.value = boolean
    }

    fun navigateToParentDone()
    {
        _navigateToParent.value = false
    }

    fun navigateToTeacher(boolean: Boolean)
    {
        _navigateToTeacher.value = boolean
    }

    fun navigateToTeacherDone()
    {
        _navigateToTeacher.value = false
    }
}