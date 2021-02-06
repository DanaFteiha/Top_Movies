package com.example.topmovies.ui.data

import android.content.Context
import android.net.ConnectivityManager
import com.example.topmovies.api.Api
import com.example.topmovies.data.Movie
import com.example.topmovies.database.MovieDataBase
import javax.inject.Inject

class Repository @Inject constructor(
    private val database: MovieDataBase,
    private val context: Context,
    private val moviesApi: Api
) {

    //A suspend function to get data from api
    suspend fun getMovies(): List<Movie> {
        var pageNum = 1
        var counter = 0
        var moviesList: List<Movie>
        if (isNetworkConnected()) {
            while (counter < Api.Api_Num_Of_Results) {
                val response = moviesApi.getTopRatedMovies(Api.API_KEY, pageNum)
                moviesList = response.results.map { result ->
                    Movie(
                        name = result.originalTitle.toString(),
                        id = result.id,
                        overview = result.overview.toString(),
                        voteAvg = result.voteAverage,
                        image = result.posterPath
                    )
                }
                counter += response.results.size
                pageNum++
                insertMovies(moviesList)
            }
        }
        return getMoviesFromDatabase()
    }

    private suspend fun insertMovies(movie: List<Movie>) =
        database.getMovieDao().insertMovies(movie)

    private suspend fun getMoviesFromDatabase() = database.getMovieDao().getAllMovies()

    private fun isNetworkConnected(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        return cm!!.activeNetworkInfo != null && cm.activeNetworkInfo.isConnected
    }

}
