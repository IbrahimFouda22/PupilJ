package com.example.schoolapp.fragments.parent.profile

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
class ProfileParentViewModel @Inject constructor(
    application: Application
) :
    AndroidViewModel(application) {
    private val _user = MutableLiveData<UserProfile>()
    val user: LiveData<UserProfile>
        get() = _user

//    init {
//        getProfile()
//    }
//
//    private fun getProfile() {
//        viewModelScope.launch {
//            try {
//                _user.value = parentManageProfileUseCase.getProfile("Bearer $token")
//            }catch (e:Exception){
//                Toast.makeText(getApplication(), e.message, Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
}