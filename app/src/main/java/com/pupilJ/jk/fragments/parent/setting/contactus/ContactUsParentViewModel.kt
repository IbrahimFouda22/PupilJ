package com.pupilJ.jk.fragments.parent.setting.contactus

import android.app.Application
import android.content.SharedPreferences
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pupilJ.domain.models.ContactUs
import com.pupilJ.domain.models.InvalidData
import com.pupilJ.domain.usecase.teacher.TeacherManageSettingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ContactUsParentViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val teacherManageSettingUseCase: TeacherManageSettingUseCase,
    application: Application
) : AndroidViewModel(application) {

    private val _settingResponse = MutableLiveData<ContactUs>()
    val settingResponse: LiveData<ContactUs> get() = _settingResponse

    fun contactUS(
        name: String,
        email: String,
        title: String,
        problem: String
    ) {
        viewModelScope.launch {
            try {
                _settingResponse.value =
                    teacherManageSettingUseCase.contactUs(
                        sharedPreferences.getString("schoolId","")!!.toInt(),name, email, title, problem)
            } catch (e: InvalidData) {
                Toast.makeText(getApplication(), e.message, Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(getApplication(), e.message.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }
}