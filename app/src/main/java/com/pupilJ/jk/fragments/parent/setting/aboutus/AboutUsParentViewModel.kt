package com.pupilJ.jk.fragments.parent.setting.aboutus

import android.app.Application
import android.content.SharedPreferences
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pupilJ.domain.models.AboutUS
import com.pupilJ.domain.models.ContactUs
import com.pupilJ.domain.models.InvalidData
import com.pupilJ.domain.usecase.parent.ParentManageSettingUseCase
import com.pupilJ.domain.usecase.teacher.TeacherManageSettingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class AboutUsParentViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val parentManageSettingUseCase: ParentManageSettingUseCase,
    application: Application
) : AndroidViewModel(application) {
    private val _settingResponse = MutableLiveData<AboutUS>()
    val settingResponse: LiveData<AboutUS> get() = _settingResponse

    init {
        aboutUs()
    }

    private fun aboutUs(
    ) {
        viewModelScope.launch {
            try {
                _settingResponse.value =
                    parentManageSettingUseCase.aboutUs(
                        sharedPreferences.getString("schoolId","")!!.toInt())
            } catch (e: Exception) {
                Toast.makeText(getApplication(), e.message.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }
}