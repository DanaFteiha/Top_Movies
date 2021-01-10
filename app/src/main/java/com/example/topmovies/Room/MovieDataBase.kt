package com.example.topmovies.Room

import android.content.Context
import android.provider.ContactsContract
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.topmovies.data.Converters
import com.example.topmovies.data.Movie
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Singleton


@Database(entities = [Movie::class], version = 1)
@TypeConverters(Converters::class)
abstract class MovieDataBase : RoomDatabase() {

    //variable to access Dao methods
    abstract fun getMovieDao(): MovieDao

    //Singleton Instance
    companion object {
        @Volatile
        private var instance: MovieDataBase? = null

        //To ensure there is only one instance from the database
        //And is run by only one thread while setting it
        private val lock = Any()

        // if the INSTANCE is not null, then return it,
        // if it is, then create the database
        operator fun invoke(context: Context) =
            instance ?: synchronized(lock) {
                instance ?: createDatabase(context).also { instance = it }
            }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                MovieDataBase::class.java,
                "movies"
            ).build()
    }

}
