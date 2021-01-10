package com.example.topmovies.data

import com.example.topmovies.Room.MovieDataBase
import com.example.topmovies.api.Api
import com.example.topmovies.api.ApiResults
import javax.inject.Inject

class Repository @Inject constructor(
    //private val database: MovieDataBase,
    private val moviesApi: Api
) {

    suspend fun getMovies(pageNum: Int) =
        moviesApi.getTopRatedMovies(Api.API_KEY, pageNum)

}
