package com.example.schoolapp.fragments.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ViewPagerViewModel @Inject constructor ():ViewModel() {
    private val _navigateToJoin = MutableLiveData(false)
    val navigateToJoin : LiveData<Boolean> get() = _navigateToJoin

    fun navigateToJoin()
    {
        _navigateToJoin.value = true
    }

    fun navigateToJoinDone()
    {
        _navigateToJoin.value = false
    }
}