package com.example.domain.usecase.teacher

import android.util.Log
import com.example.domain.models.Attendance
import com.example.domain.models.ChildrenTeacher
import com.example.domain.models.EmptyDataException
import com.example.domain.repo.teacherrepo.TeacherRepo
import javax.inject.Inject

class TeacherManageAttendanceUseCase @Inject constructor(private val teacherRepo: TeacherRepo) {
    suspend fun setAttendance(
        attendType: String,
        attendDate: String,
        children: List<ChildrenTeacher>
    ): Attendance {
        if (children.isEmpty())
            throw EmptyDataException("No Students Selected")
        val list = children.map {
            childrenTeacherToId(it)
        }
        return teacherRepo.setAttendance(attendType, attendDate, list)
    }



    private fun childrenTeacherToId(childrenTeacher: ChildrenTeacher): Int {
        return childrenTeacher.id
    }
}