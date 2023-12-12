//package com.example.schoolapp.di
//
//import com.example.data.remote.ApiService
//import com.example.data.remote.IRemoteDataSource
//import com.example.data.remote.RemoteDataSource
//import dagger.Binds
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.android.components.ViewModelComponent
//import dagger.hilt.android.scopes.ActivityScoped
//import dagger.hilt.components.SingletonComponent
//import javax.inject.Singleton
//
//@Module
//@InstallIn(SingletonComponent::class)
//abstract class RemoteDataSourceModule {
//
//    @Binds
//    abstract fun provideRemoteDataSource(remoteDataSource: RemoteDataSource):IRemoteDataSource
//}