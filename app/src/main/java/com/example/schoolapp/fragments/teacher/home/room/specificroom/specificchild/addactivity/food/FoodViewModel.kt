package com.example.schoolapp.fragments.teacher.home.room.specificroom.specificchild.addactivity.food

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.AddActivity
import com.example.domain.models.AdditionalFieldFood
import com.example.domain.models.MealType
import com.example.domain.usecase.teacher.TeacherManageAddActivityUseCase
import com.example.domain.usecase.teacher.TeacherManageMealUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodViewModel @Inject constructor(
    private val teacherManageMealUseCase: TeacherManageMealUseCase,
    private val teacherManageAddActivityUseCase: TeacherManageAddActivityUseCase,
    application: Application
) : AndroidViewModel(application) {

    private val _mealType = MutableLiveData<List<MealType>>()
    val mealType: LiveData<List<MealType>> get() = _mealType

    private val _mealItems = MutableLiveData<List<MealType>>()
    val mealItems: LiveData<List<MealType>> get() = _mealItems

    private val _addFoodResponse = MutableLiveData<AddActivity>()
    val addFoodResponse: LiveData<AddActivity> get() = _addFoodResponse

    private val _mealItemSelected = MutableLiveData<String>()
    val mealItemSelected: LiveData<String> get() = _mealItemSelected

    init {
        getMealType()
    }

    fun addFood(
        activityType: String,
        additionalFieldFood: AdditionalFieldFood,
        mealItems: List<Int>,
        mealId: Int,
        childId: Int,
        activityDate: String,
        notes:String?
    ) {
        viewModelScope.launch {
            try {
                _addFoodResponse.value = teacherManageAddActivityUseCase.addFoodActivity(
                    activityType, additionalFieldFood, mealItems, mealId,childId, activityDate,notes
                )
            } catch (e: Exception) {
                Toast.makeText(getApplication(), e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun setMealItemSelect(select: String) {
        _mealItemSelected.value = select
    }


    private fun getMealType() {
        viewModelScope.launch {
            try {
                _mealType.value = teacherManageMealUseCase.getMealType()
            } catch (e: Exception) {
                Toast.makeText(getApplication(), e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun getMealItems(mealTypeId: Int) {
        viewModelScope.launch {
            try {
                _mealItems.value = teacherManageMealUseCase.getMealItems(mealTypeId)
            } catch (e: Exception) {
                Toast.makeText(getApplication(), e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}