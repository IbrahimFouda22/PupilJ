package com.example.schoolapp.fragments.teacher.home.room

import android.app.Application
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.example.domain.models.Classes
import com.example.domain.models.NoInternetException
import com.example.domain.usecase.teacher.TeacherManageClasses
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomsViewModel @Inject constructor(
    private val teacherManageClasses: TeacherManageClasses,
    //private val sharedPreferences: SharedPreferences,
    application: Application
) :
    AndroidViewModel(application) {
    private val _rooms = MutableLiveData<List<Classes>>()
    val rooms: LiveData<List<Classes>>
        get() = _rooms

    init {
        getRooms()
    }

    private fun getRooms() {
//        val token = if(sharedPreferences.getString("token",null)==null)
//            getSharedPref().getString("token")
        viewModelScope.launch {
            try {
                _rooms.value = teacherManageClasses.getClasses()
            } catch (e: NoInternetException) {
                Toast.makeText(getApplication(), e.message, Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(getApplication(), e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
//    private fun getSharedPref() : SharedPreferences {
//        // Initialize/open an instance of EncryptedSharedPreferences on below line.
//        return EncryptedSharedPreferences.create(
//            // passing a file name to share a preferences
//            getApplication(),
//            "secret_shared_prefs",
//            getMasterKey(),
//            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
//            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
//        )
//    }
//    private fun getMasterKey(): MasterKey {
//        return MasterKey.Builder(getApplication())
//            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build()
//    }
}