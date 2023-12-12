package com.example.schoolapp.di

import com.example.data.remote.ApiService
import com.example.data.repo.TeacherRepoImpl
import com.example.domain.repo.teacherrepo.TeacherRepo
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
    fun provideTeacherRepo(apiService: ApiService):TeacherRepo{
        return TeacherRepoImpl(apiService)
    }
}