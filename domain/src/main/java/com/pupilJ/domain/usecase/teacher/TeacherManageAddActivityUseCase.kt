package com.pupilJ.domain.usecase.teacher

import com.pupilJ.domain.models.AddActivity
import com.pupilJ.domain.models.AdditionalFieldFood
import com.pupilJ.domain.models.AdditionalFieldNameToFace
import com.pupilJ.domain.models.AdditionalFieldNap
import com.pupilJ.domain.models.AdditionalFieldPotty
import com.pupilJ.domain.models.EmptyDataException
import com.pupilJ.domain.repo.teacherrepo.TeacherRepo
import javax.inject.Inject

class TeacherManageAddActivityUseCase @Inject constructor(private val teacherRepo: TeacherRepo) {
    suspend fun addFoodActivity(
        activityType: String,
        additionalFieldFood: AdditionalFieldFood,
        mealItems: List<Int>,
        mealId: Int,
        childId: Int,
        activityDate: String,
        notes: String?
    ): AddActivity {
        validateFoodData(additionalFieldFood, mealItems)
        return teacherRepo.addFoodActivity(
            activityType,
            additionalFieldFood,
            mealItems,
            mealId,
            childId,
            activityDate,
            notes
        )
    }

    suspend fun addNapActivity(
        activityType: String,
        additionalFieldNap: AdditionalFieldNap,
        childId: Int,
        activityDate: String,
        notes: String?
    ): AddActivity {
        validateNapData(additionalFieldNap)
        return teacherRepo.addNapActivity(
            activityType,
            additionalFieldNap,
            childId,
            activityDate,
            notes
        )
    }

    suspend fun addPottyActivity(
        activityType: String,
        additionalFieldPotty: AdditionalFieldPotty,
        childId: Int,
        activityDate: String,
        notes: String?
    ): AddActivity {
        validatePottyData(additionalFieldPotty)
        return teacherRepo.addPottyActivity(
            activityType,
            additionalFieldPotty,
            childId,
            activityDate,
            notes
        )
    }

    suspend fun addNoteMedIncidentActivity(
        activityType: String,
        childId: Int,
        activityDate: String,
        notes: String?
    ): AddActivity {
        validateNoteMedIncidentData(notes)
        return teacherRepo.addNoteMedIncidentsActivity(
            activityType,
            childId,
            activityDate,
            notes
        )
    }

    suspend fun addAchievementActivity(
        activityType: String,
        childId: Int,
        activityDate: String,
        notes: String?,
        image: String?
    ): AddActivity {
        validateAchievementData(notes, image)
        return teacherRepo.addAchievementActivity(
            activityType,
            childId,
            activityDate,
            notes,
            image
        )
    }

    suspend fun addCustomActivity(
        activityType: String,
        childId: Int,
        activityDate: String,
        notes: String?,
        category: Int?,
        progress: Int?,
        scales: Int?,
    ): AddActivity {
        validateCustomData(category, progress, scales)
        return teacherRepo.addCustomActivity(
            activityType,
            childId,
            activityDate,
            notes,
            category!!,
            progress!!,
            scales!!
        )
    }

    suspend fun addObservationActivity(
        activityType: String,
        childId: Int,
        activityDate: String,
        notes: String?,
        mileStoneId: Int?,
        staffOnly: Int?,
    ): AddActivity {
        validateObservationData(mileStoneId, notes)
        return teacherRepo.addObservationActivity(
            activityType,
            childId,
            activityDate,
            notes,
            mileStoneId!!,
            staffOnly!!
        )
    }


    suspend fun addHealthCheckActivity(
        activityType: String,
        childId: Int,
        activityDate: String,
        notes: String?,
        temperature: String?
    ): AddActivity {
        validateHealthCheckData(temperature,notes)
        return teacherRepo.addHealthCheckActivity(
            activityType,
            childId,
            activityDate,
            notes,
            temperature!!
        )
    }

    suspend fun addNameToFaceActivity(
        activityType: String,
        additionalFieldNameToFace: AdditionalFieldNameToFace,
        childId: Int,
        activityDate: String,
        notes: String?,
        image: String?
    ): AddActivity {
        validateNAmeToFaceData(additionalFieldNameToFace,notes,image)
        return teacherRepo.addNameToFaceActivity(
            activityType,
            additionalFieldNameToFace,
            childId,
            activityDate,
            notes!!,
            image!!
        )
    }

    private fun validateNAmeToFaceData(
        additionalFieldNameToFace: AdditionalFieldNameToFace,
        notes: String?,
        image: String?
    ) {
        if (additionalFieldNameToFace.type!! == "-1")
            throw EmptyDataException("Choose Name to Face Type")
        if (image.isNullOrEmpty())
            throw EmptyDataException("Please take a Photo")
        if (notes.isNullOrEmpty())
            throw EmptyDataException("Please Write Notes")
    }

    private fun validateHealthCheckData(temperature: String?,notes: String?) {
        if (temperature.isNullOrEmpty())
            throw EmptyDataException("Please Write Temperature")
        if (notes.isNullOrEmpty())
            throw EmptyDataException("Please Write Notes")
    }


    private fun validateFoodData(
        additionalFieldFood: AdditionalFieldFood,
        mealItems: List<Int>,
    ) {
        if (additionalFieldFood.food_type!! == "-1")
            throw EmptyDataException("Choose Food Type")
        if (additionalFieldFood.eat_type!! == "-1")
            throw EmptyDataException("Choose Eat Type")
        if (mealItems.isEmpty())
            throw EmptyDataException("Add Meal")
    }

    private fun validateNapData(
        additionalFieldNap: AdditionalFieldNap,
    ) {
        if (additionalFieldNap.nap_type!! == "-1")
            throw EmptyDataException("Choose Nap Type")
    }

    private fun validatePottyData(
        additionalFieldPotty: AdditionalFieldPotty,
    ) {
        if (additionalFieldPotty.waste_shape!! == "-1")
            throw EmptyDataException("Choose Waste Shape")
        if (additionalFieldPotty.waste_type!! == "-1")
            throw EmptyDataException("Choose Waste Type")
    }

    private fun validateNoteMedIncidentData(
        textArea: String?,
    ) {
        if (textArea.isNullOrEmpty())
            throw EmptyDataException("Please Write Notes")

    }

    private fun validateAchievementData(
        textArea: String?,
        image: String?
    ) {
        if (image.isNullOrEmpty())
            throw EmptyDataException("Please take a photo")
        if (textArea.isNullOrEmpty())
            throw EmptyDataException("Please Write Notes")

    }

    private fun validateCustomData(
        category: Int?,
        progress: Int?,
        scales: Int?
    ) {
        if (category == null)
            throw EmptyDataException("choose a category")
        if (progress == null)
            throw EmptyDataException("choose a progress")
        if (scales == null)
            throw EmptyDataException("choose a scale")

    }

    private fun validateObservationData(mileStoneId: Int?, notes: String?) {
        if (mileStoneId == null)
            throw EmptyDataException("choose a milestone")
        if (notes.isNullOrEmpty())
            throw EmptyDataException("Please Write Notes")
    }


}