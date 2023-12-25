package com.pupilJ.jk.di
import com.pupilJ.data.remote.ApiService
import com.pupilJ.data.repo.ParentRepoImpl
import com.pupilJ.domain.repo.parentrepo.ParentRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ParentRepoModule {

    @Provides
    fun provideParentRepo(apiService: ApiService): ParentRepo {
        return ParentRepoImpl(apiService)
    }
}