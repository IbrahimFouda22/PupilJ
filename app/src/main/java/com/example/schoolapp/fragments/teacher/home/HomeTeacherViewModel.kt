package com.example.schoolapp.fragments.teacher.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeTeacherViewModel : ViewModel() {
    private val _navigateToRooms = MutableLiveData(false)
    val navigateToRooms : LiveData<Boolean> get() = _navigateToRooms

    fun navigateToRooms(boolean: Boolean){
        _navigateToRooms.value = boolean
    }
    fun navigateToRoomsDone(){
        _navigateToRooms.value = false
    }
}