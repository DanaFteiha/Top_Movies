package com.example.topmovies.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.topmovies.Room.MovieDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(mContext: Context): MovieDataBase {
        return Room.databaseBuilder(
            mContext.applicationContext,
            MovieDataBase::class.java,
            "movies"
        ).build()
    }

}