package com.pupilJ.jk.fragments.teacher.home.room.specificroom.specificchild.addactivity

import android.app.Application
import android.graphics.Bitmap
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pupilJ.domain.models.PhotoVideo
import com.pupilJ.domain.usecase.teacher.TeacherUploadImageAndVideoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class AddActivityViewModel @Inject constructor(
    private val teacherUploadImageAndVideoUseCase: TeacherUploadImageAndVideoUseCase,
    application: Application
) : AndroidViewModel(application) {

    private val _addPhotoResponse = MutableLiveData<PhotoVideo>()
    val addPhotoResponse: LiveData<PhotoVideo> get() = _addPhotoResponse

    private val _showResponsePhotoAndVideo = MutableLiveData(false)
    val showResponsePhotoAndVideo: LiveData<Boolean> get() = _showResponsePhotoAndVideo

    private val _navigateToFood = MutableLiveData(false)
    val navigateToFood: LiveData<Boolean> get() = _navigateToFood

    private val _navigateToNap = MutableLiveData(false)
    val navigateToNap: LiveData<Boolean> get() = _navigateToNap

    private val _navigateToPotty = MutableLiveData(false)
    val navigateToPotty: LiveData<Boolean> get() = _navigateToPotty

    private val _navigateToNote = MutableLiveData(false)
    val navigateToNote: LiveData<Boolean> get() = _navigateToNote

    private val _navigateToAchievement = MutableLiveData(false)
    val navigateToAchievement: LiveData<Boolean> get() = _navigateToAchievement

    private val _navigateToCustom = MutableLiveData(false)
    val navigateToCustom: LiveData<Boolean> get() = _navigateToCustom

    private val _navigateToMeds = MutableLiveData(false)
    val navigateToMeds: LiveData<Boolean> get() = _navigateToMeds

    private val _navigateToNameToFace = MutableLiveData(false)
    val navigateToNameToFace: LiveData<Boolean> get() = _navigateToNameToFace

    private val _navigateToIncidents = MutableLiveData(false)
    val navigateToIncidents: LiveData<Boolean> get() = _navigateToIncidents

    private val _navigateToHealthCheck = MutableLiveData(false)
    val navigateToHealthCheck: LiveData<Boolean> get() = _navigateToHealthCheck

    private val _navigateToObservation = MutableLiveData(false)
    val navigateToObservation: LiveData<Boolean> get() = _navigateToObservation

    fun uploadImage(bitmap: Bitmap,childId:String){
        viewModelScope.launch {
                _showResponsePhotoAndVideo.value = true
            try {
                _addPhotoResponse.value = teacherUploadImageAndVideoUseCase.uploadImage(bitmap,getApplication(),"photo",childId)
            }catch (e:Exception){
                Toast.makeText(getApplication(),e.message.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun uploadVideo(file: File,childId:String){
        viewModelScope.launch {
                _showResponsePhotoAndVideo.value = true
            try {
                _addPhotoResponse.value = teacherUploadImageAndVideoUseCase.uploadVideo(file,"video",childId)
            }catch (e:Exception){
                Toast.makeText(getApplication(),e.message.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun showResponsePhotoAndVideo(){
        _showResponsePhotoAndVideo.value = false
    }

    fun navigateToFood() {
        _navigateToFood.value = true
    }

    fun navigateToFoodDone() {
        _navigateToFood.value = false
    }

    fun navigateToNap() {
        _navigateToNap.value = true
    }

    fun navigateToNapDone() {
        _navigateToNap.value = false
    }

    fun navigateToPotty() {
        _navigateToPotty.value = true
    }

    fun navigateToPottyDone() {
        _navigateToPotty.value = false
    }

    fun navigateToNote() {
        _navigateToNote.value = true
    }

    fun navigateToNoteDone() {
        _navigateToNote.value = false
    }

    fun navigateToAchievement() {
        _navigateToAchievement.value = true
    }

    fun navigateToAchievementDone() {
        _navigateToAchievement.value = false
    }

    fun navigateToCustom() {
        _navigateToCustom.value = true
    }

    fun navigateToCustomDone() {
        _navigateToCustom.value = false
    }

    fun navigateToMeds() {
        _navigateToMeds.value = true
    }

    fun navigateToMedsDone() {
        _navigateToMeds.value = false
    }

    fun navigateToNameToFace() {
        _navigateToNameToFace.value = true
    }

    fun navigateToNameToFaceDone() {
        _navigateToNameToFace.value = false
    }

    fun navigateToIncidents() {
        _navigateToIncidents.value = true
    }

    fun navigateToIncidentsDone() {
        _navigateToIncidents.value = false
    }

    fun navigateToHealthCheck() {
        _navigateToHealthCheck.value = true
    }

    fun navigateToHealthCheckDone() {
        _navigateToHealthCheck.value = false
    }

    fun navigateToObservation() {
        _navigateToObservation.value = true
    }

    fun navigateToObservationDone() {
        _navigateToObservation.value = false
    }
}