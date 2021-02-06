package com.example.topmovies.Room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.topmovies.data.Movie


@Database(entities = [Movie::class], version = 1)
abstract class MovieDataBase : RoomDatabase() {
    abstract fun getMovieDao(): MovieDao
}
