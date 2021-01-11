package com.example.topmovies.Room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.topmovies.data.Movie

//The DAO class inserts and queries the data from the database
@Dao
interface  MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movie: List<Movie>)

    @Query("Select * FROM movie_table")
    suspend fun getAllMovies():List<Movie>
    //not a suspend function since it will return liveData
}