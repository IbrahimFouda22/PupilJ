package com.pupilJ.jk.di


import com.pupilJ.data.remote.ApiService
import com.pupilJ.data.repo.TeacherRepoImpl
import com.pupilJ.domain.repo.teacherrepo.TeacherRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TeacherRepoModule {
    @Provides
    @Singleton
    fun provideTeacherRepo(apiService: ApiService): TeacherRepo {
        return TeacherRepoImpl(apiService)
    }
}