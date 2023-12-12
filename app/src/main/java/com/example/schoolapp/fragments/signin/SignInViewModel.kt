package com.example.schoolapp.fragments.signin

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.models.EmptyDataException
import com.example.domain.models.User
import com.example.domain.usecase.parent.ParentAuthUseCase
import com.example.domain.usecase.parent.ValidationUseCase
import com.example.domain.usecase.teacher.TeacherAuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val parentAuthUseCase: ParentAuthUseCase,
    private val teacherAuthUseCase: TeacherAuthUseCase,
    private val validationUseCase: ValidationUseCase,
    application: Application
) : AndroidViewModel(application) {

    private val _user = MutableLiveData<User>()
    val user : LiveData<User>get() = _user

    private val _showProgress = MutableLiveData(false)
    val showProgress : LiveData<Boolean>get() = _showProgress

    private val _navigateToHomeParent = MutableLiveData(false)
    val navigateToHomeParent : LiveData<Boolean>get() = _navigateToHomeParent


    fun login(nationalId: String, password: String,actor:String){
        viewModelScope.launch() {
            try {
                showProgress(true)
                validationUseCase.validateNationId(nationalId)
                validationUseCase.validatePass(password)
                if(actor == "parent") {
                    _user.value = parentAuthUseCase.login(nationalId, password)
                    showProgress(false)
                    navigateToHomeParent(true)
                }
                else {
                    _user.value = teacherAuthUseCase.login(nationalId, password)
                    showProgress(false)
                    navigateToHomeParent(true)
                }

            } catch (e: Exception) {
                showProgress(false)
                if(e.message != null) {
                    Toast.makeText(getApplication(), e.message, Toast.LENGTH_SHORT).show()
                }
            }catch (e:EmptyDataException){
                Toast.makeText(getApplication(), e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun navigateToHomeParent(boolean: Boolean){
        _navigateToHomeParent.value = boolean
    }
    fun navigateToHomeParentDone(){
        _navigateToHomeParent.value = false
    }
    fun showProgress(boolean: Boolean){
        _showProgress.value = boolean
    }
}