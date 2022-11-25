package com.hms.learnyourenvironment

import android.app.Application
import com.huawei.hms.mlsdk.common.MLApplication
import dagger.hilt.android.HiltAndroidApp

/**
 * Core application class
 */
@HiltAndroidApp
class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize ML Kit
        MLApplication.initialize(this)

        // Set API Key
        MLApplication.getInstance().apiKey =BuildConfig.API_KEY
    }
}