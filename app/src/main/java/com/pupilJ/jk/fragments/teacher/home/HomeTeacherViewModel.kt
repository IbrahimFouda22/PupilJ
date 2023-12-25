package com.pupilJ.jk.fragments.teacher.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeTeacherViewModel : ViewModel() {
    private val _navigateToRooms = MutableLiveData(false)
    val navigateToRooms : LiveData<Boolean> get() = _navigateToRooms

    private val _navigateToStudents = MutableLiveData(false)
    val navigateToStudents : LiveData<Boolean> get() = _navigateToStudents

    private val _navigateToReminder = MutableLiveData(false)
    val navigateToReminder : LiveData<Boolean> get() = _navigateToReminder

    fun navigateToRooms(){
        _navigateToRooms.value = true
    }
    fun navigateToRoomsDone(){
        _navigateToRooms.value = false
    }

    fun navigateToReminder(){
        _navigateToReminder.value = true
    }
    fun navigateToReminderDone(){
        _navigateToReminder.value = false
    }

    fun navigateToStudents(){
        _navigateToStudents.value = true
    }
    fun navigateToStudentsDone(){
        _navigateToStudents.value = false
    }
}