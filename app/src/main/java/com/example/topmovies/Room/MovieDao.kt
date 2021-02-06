package com.example.topmovies.Room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.topmovies.data.Movie

//The DAO interface inserts and queries the data from the database
@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movie: List<Movie>)

    @Query("Select * FROM movie_table")
    suspend fun getAllMovies(): List<Movie>
}