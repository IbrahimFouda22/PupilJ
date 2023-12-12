package com.example.data.mapper

import com.example.data.dto.AttendanceDto
import com.example.data.dto.ChildrenDto
import com.example.data.dto.ChildrenDtoTeacher
import com.example.data.dto.ChildrenDtoTeacherData
import com.example.data.dto.ClassesData
import com.example.data.dto.ClassesDto
import com.example.data.dto.DataChildren
import com.example.data.dto.LoginDto
import com.example.data.dto.PhotoVideoDto
import com.example.data.dto.ProfileDto
import com.example.domain.models.Attendance
import com.example.domain.models.Children
import com.example.domain.models.ChildrenTeacher
import com.example.domain.models.Classes
import com.example.domain.models.PhotoVideo
import com.example.domain.models.User
import com.example.domain.models.UserProfile


fun LoginDto.toEntity(): User {
    return User(
        address = data.address,
        email = data.email,
        id = data.id,
        name = data.name,
        national_id = data.national_id,
        phone_number = data.phone_number,
        profile_photo_url = data.profile_photo_url,
        token = data.token,
        schoolId = data.school.id
    )
}

fun ProfileDto.toEntity(): UserProfile {
    return UserProfile(
        email = data.email,
        name = data.name,
        phone = data.phone_number,
        profilePhoto = data.profile_photo_path
    )
}

fun classDtoToEntity(classesDto: ClassesDto): List<Classes> {
    return classesDto.data.map { classToEntity(it) }
}

fun classToEntity(classesData: ClassesData): Classes {
    return Classes(
        id = classesData.id,
        name = classesData.name,
        schoolId = classesData.school_id
    )
}

fun childrenDtoOtEntity(children: ChildrenDto): List<Children> {
    return children.data.map { toEntity(it) }
}

fun toEntity(children: DataChildren): Children {
    return Children(
        firstName = children.first_name,
        id = children.id,
        lastName = children.last_name,
        name = children.name,
        profilePhotoUrl = children.profile_photo_url,
        type = children.type,
    )
}

fun childrenTeacherDtoToEntity(childrenDtoTeacher: ChildrenDtoTeacher): List<ChildrenTeacher> {
    return childrenDtoTeacher.data.map {
        childrenToEntity(it)
    }
}

fun childrenToEntity(childrenDtoTeacherData: ChildrenDtoTeacherData): ChildrenTeacher {
    return ChildrenTeacher(
        address1 = childrenDtoTeacherData.address_1,
        address2 =childrenDtoTeacherData.address_2,
        city =childrenDtoTeacherData.city,
        dob =childrenDtoTeacherData.dob,
        doctorName =childrenDtoTeacherData.doctor_name,
        doctorPhone =childrenDtoTeacherData.doctor_phone,
        firstName =childrenDtoTeacherData.first_name,
        id =childrenDtoTeacherData.id,
        lastName =childrenDtoTeacherData.last_name,
        name =childrenDtoTeacherData.name,
        notes =childrenDtoTeacherData.notes,
        nurseryClassId =childrenDtoTeacherData.nursery_class_id,
        parentId =childrenDtoTeacherData.parent_id,
        profilePhotoUrl =childrenDtoTeacherData.profile_photo_url,
        schoolId =childrenDtoTeacherData.school_id,
        status = childrenDtoTeacherData.status,
        medicine = childrenDtoTeacherData.medicine,
        allergies = childrenDtoTeacherData.meals_allergies,
        attendToday = childrenDtoTeacherData.attend_today,
        fatherNum = childrenDtoTeacherData.father_number,
        motherNumber = childrenDtoTeacherData.mother_number,
        pickUpTime = childrenDtoTeacherData.pickup_time,
    )
}
fun PhotoVideoDto.toEntity() : PhotoVideo{
    return PhotoVideo(
        status = status
    )
}

fun AttendanceDto.toEntity():Attendance{
    return Attendance(
        status = status
    )
}