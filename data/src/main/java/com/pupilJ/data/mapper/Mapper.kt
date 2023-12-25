package com.pupilJ.data.mapper

import com.pupilJ.data.dto.AboutUsDto
import com.pupilJ.data.dto.AddReminderDto
import com.pupilJ.data.dto.AttendanceDto
import com.pupilJ.data.dto.ChildrenDto
import com.pupilJ.data.dto.ChildrenDtoTeacher
import com.pupilJ.data.dto.ChildrenDtoTeacherData
import com.pupilJ.data.dto.ClassesData
import com.pupilJ.data.dto.ClassesDto
import com.pupilJ.data.dto.ContactUsDto
import com.pupilJ.data.dto.DataChildren
import com.pupilJ.data.dto.DeleteReminderDto
import com.pupilJ.data.dto.LoginDto
import com.pupilJ.data.dto.MessageDto
import com.pupilJ.data.dto.MessageDtoData
import com.pupilJ.data.dto.ParentDto
import com.pupilJ.data.dto.ParentDtoData
import com.pupilJ.data.dto.PhotoVideoDto
import com.pupilJ.data.dto.ProfileDto
import com.pupilJ.data.dto.ReminderDto
import com.pupilJ.data.dto.ReminderDtoData
import com.pupilJ.data.dto.SendMessageDto
import com.pupilJ.data.dto.StartRoomDto
import com.pupilJ.data.dto.TeacherDto
import com.pupilJ.data.dto.TeacherDtoData
import com.pupilJ.domain.models.AboutUS
import com.pupilJ.domain.models.AddReminder
import com.pupilJ.domain.models.Attendance
import com.pupilJ.domain.models.Children
import com.pupilJ.domain.models.ChildrenTeacher
import com.pupilJ.domain.models.Classes
import com.pupilJ.domain.models.ContactUs
import com.pupilJ.domain.models.DeleteReminder
import com.pupilJ.domain.models.Message
import com.pupilJ.domain.models.Parent
import com.pupilJ.domain.models.PhotoVideo
import com.pupilJ.domain.models.Reminder
import com.pupilJ.domain.models.StartRoom
import com.pupilJ.domain.models.Teacher
import com.pupilJ.domain.models.User
import com.pupilJ.domain.models.UserProfile


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
        checkedIn = childrenDtoTeacherData.check_in,
        checkedOut = childrenDtoTeacherData.check_out,
    )
}
fun PhotoVideoDto.toEntity() : PhotoVideo {
    return PhotoVideo(
        status = status
    )
}

fun AttendanceDto.toEntity(): Attendance {
    return Attendance(
        status = status
    )
}

fun ContactUsDto.toEntity() : ContactUs{
    return ContactUs(
        msg = msg
    )
}

fun ReminderDto.toEntity(): List<Reminder>{
    return data.data.map {
        reminderDataToEntity(it)
    }
}

fun reminderDataToEntity(reminderDtoData: ReminderDtoData):Reminder{
    return Reminder(
        id = reminderDtoData.id,
        activityType = reminderDtoData.type,
        childId = reminderDtoData.child_id,
        reminderDate = reminderDtoData.reminder_date,
        repeatUntilCheckout = reminderDtoData.repeat_until_checkout,
        photo = reminderDtoData.child.profile_photo_url,
        childName = reminderDtoData.child.name
    )
}

fun AddReminderDto.toEntity():AddReminder{
    return AddReminder(
        status = status
    )
}

fun DeleteReminderDto.toEntity():DeleteReminder{
    return DeleteReminder(
        msg = msg,
        status = status
    )
}

fun MessageDto.toEntity():List<Message>{
    return data.map {
        messageDtoDataToEntity(it)
    }
}

fun messageDtoDataToEntity(messageDtoData: MessageDtoData):Message{
    return Message(
        userId = messageDtoData.from,
        msg = messageDtoData.message,
        msgTime = messageDtoData.created_at

    )
}

fun StartRoomDto.toEntity():StartRoom{
    return StartRoom(
        chatId = data.chat_id
    )
}

fun SendMessageDto.toEntity():Message{
    return Message(
        userId = data.from,
        msg = data.message,
    )
}

fun ParentDto.toEntity():List<Parent>{
    return data.map {
        parentDtoDataToEntity(it)
    }
}

fun parentDtoDataToEntity(parentDtoData: ParentDtoData):Parent{
    return Parent(
        id = parentDtoData.id,
        name = parentDtoData.name,
        dob = parentDtoData.dob,
        profilePhotoUrl = parentDtoData.profile_photo_url,
        email = parentDtoData.email,
    )
}

fun TeacherDto.toEntity():List<Teacher>{
    return data.map {
        parentDtoDataToEntity(it)
    }
}

fun parentDtoDataToEntity(parentDtoData: TeacherDtoData):Teacher{
    return Teacher(
        id = parentDtoData.id,
        name = parentDtoData.name,
        dob = parentDtoData.dob,
        profilePhotoUrl = parentDtoData.profile_photo_url,
        email = parentDtoData.email,
    )
}

fun AboutUsDto.toEntity():AboutUS{
    return AboutUS(
        text = data.value
    )
}