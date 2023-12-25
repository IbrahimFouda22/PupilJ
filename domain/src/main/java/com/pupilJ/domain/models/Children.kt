package com.pupilJ.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Children(
    val firstName: String,
    val id: Int,
    val lastName: String,
    val name: String,
    val profilePhotoUrl: String,
    val type: String
) : Parcelable