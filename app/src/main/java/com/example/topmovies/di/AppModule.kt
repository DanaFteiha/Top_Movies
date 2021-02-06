package com.example.topmovies.di

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

//A module to provide application context
@Module
class AppModule(val context: Context) {
    @Provides
    @Singleton
    fun providesApplicationContext(): Context = context.applicationContext
}