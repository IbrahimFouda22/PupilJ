package com.pupilJ.domain.usecase.parent

import com.pupilJ.domain.models.EmptyDataException
import javax.inject.Inject

class ValidationUseCase @Inject constructor() {
    fun validateNationId(nationId:String){
        if(nationId.isNullOrEmpty())
            throw EmptyDataException("National ID is Empty")
    }
    fun validatePass(pass:String){
        if(pass.isNullOrEmpty())
            throw EmptyDataException("Password ID is Empty")
    }
}