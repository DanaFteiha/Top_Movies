package com.example.topmovies.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


//interface that defines how Retrofit talks to the web server using HTTP requests
//Returns a Coroutine [List] of [Movies] which can be fetched with await() if in a Coroutine scope.

interface Api {

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val API_KEY = "2bce1ad0d3d0586476f1b9ed1701b22a"
        const val IMAGE_URL = "https://image.tmdb.org/t/p/w500/"
        const val Api_Num_Of_Results =100
    }

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("api_key") api_key: String,
    @Query("page") page : Int)
    :ApiResults

}
