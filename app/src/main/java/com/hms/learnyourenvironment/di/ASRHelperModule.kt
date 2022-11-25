package com.hms.learnyourenvironment.di

import android.content.Context
import com.hms.learnyourenvironment.utils.ASRHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// ASR Module with Hilt
@InstallIn(SingletonComponent::class)
@Module
object ASRHelperModule {
    @Provides
    @Singleton
    fun asrHelper(@ApplicationContext appContext: Context): ASRHelper =
        ASRHelper(appContext)
}