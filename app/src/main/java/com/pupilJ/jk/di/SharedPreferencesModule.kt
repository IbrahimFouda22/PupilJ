package com.pupilJ.jk.di

import android.content.SharedPreferences
import com.pupilJ.jk.util.SharedPrefManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SharedPreferencesModule {

    @Provides
    @Singleton
    fun provideSharedPref(sharedPrefManager: SharedPrefManager): SharedPreferences =
        sharedPrefManager.getSharedPref()
}
