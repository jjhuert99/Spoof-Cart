package com.example.spoofcart.di

import android.app.Application
import android.content.Context
import com.example.spoofcart.MainActivity
import com.example.spoofcart.SpoofCart
import com.example.spoofcart.sharedpref.SharedPref
import com.example.spoofcart.sharedpref.SharedPrefImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object SharedPrefsModule {

    @Singleton
    @Provides
    fun provideSharedPrefs(@ApplicationContext context: Context): SharedPref{
        return SharedPrefImpl(context)
    }
}
