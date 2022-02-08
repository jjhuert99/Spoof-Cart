package com.example.spoofcart

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SpoofCart : Application(){
    override fun onCreate() {
        super.onCreate()
    }
}
