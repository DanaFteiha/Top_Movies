package com.example.topmovies.data

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.core.content.ContextCompat.getSystemService
import com.example.topmovies.Resource
import com.example.topmovies.Room.MovieDataBase
import com.example.topmovies.api.Api
import com.example.topmovies.api.ApiResults
import javax.inject.Inject


class Repository @Inject constructor(
    private val database: MovieDataBase,
    private val context: Context,
    private val moviesApi: Api
) {

    //A suspend function to get data from api using the retrofit instance
    suspend fun getMovies(pageNum: Int): List<Movie> {
        if (isNetworkConnected()) {
            //moviesResponse.postValue(Resource.Loading())
            val response = moviesApi.getTopRatedMovies(Api.API_KEY, pageNum)
            val moviesList = response.results.map { result ->
                Movie(
                    name = result.originalTitle.toString(),
                    id = result.id,
                    overview = result.overview.toString(),
                    voteAvg = result.voteAverage,
                    image = result.posterPath
                )
            }
            Log.d("debug", "getMovies: ")
            insertMovies(moviesList)
            return moviesList
            // saveMovies(response)
            // moviesResponse.postValue(handleMoviesResponse(response))
        } else {
            return getMoviesFromDatabase()
        }
    }

    suspend fun insertMovies(movie: List<Movie>) = database.getMovieDao().insertMovies(movie)

    suspend fun getMoviesFromDatabase() = database.getMovieDao().getAllMovies()


    private fun isNetworkConnected(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        return cm!!.activeNetworkInfo != null && cm.activeNetworkInfo.isConnected
    }

}
