package com.example.schoolapp.di

import com.example.domain.repo.parentrepo.ParentRepo
import com.example.domain.usecase.parent.ParentAuthUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

//@Module
//@InstallIn(SingletonComponent::class)
//object UseCaseModule {
//    @Provides
//    fun provideUseCase(parentRepo: ParentRepo): ParentAuthUseCase {
//        return ParentAuthUseCase(parentRepo)
//    }
//}