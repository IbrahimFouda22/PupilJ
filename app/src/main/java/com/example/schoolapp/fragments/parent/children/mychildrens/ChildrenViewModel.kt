package com.example.schoolapp.fragments.parent.children.mychildrens

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Children
import com.example.domain.models.NoInternetException
import com.example.domain.models.User
import com.example.domain.usecase.parent.ParentChildrenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChildrenViewModel @Inject constructor(private val parentChildrenUseCase: ParentChildrenUseCase,
                                            application: Application
) :
    AndroidViewModel(application) {
    private val _children = MutableLiveData<List<Children>>()
    val children : LiveData<List<Children>>
        get() = _children

    init {
        getChildren()
    }

    private fun getChildren(){
        viewModelScope.launch {
            try {
            _children.value = parentChildrenUseCase.getChildren()
            }catch (e:NoInternetException){
                Toast.makeText(getApplication(), e.message, Toast.LENGTH_SHORT).show()
            }catch (e: Exception) {
                Toast.makeText(getApplication(), e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}