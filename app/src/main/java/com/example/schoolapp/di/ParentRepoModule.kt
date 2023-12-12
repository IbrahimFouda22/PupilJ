package com.example.schoolapp.di

import com.example.data.remote.ApiService
import com.example.data.repo.ParentRepoImpl
import com.example.domain.repo.parentrepo.ParentRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ParentRepoModule {

    @Provides
    fun provideParentRepo(apiService: ApiService):ParentRepo{
        return ParentRepoImpl(apiService)
    }
}