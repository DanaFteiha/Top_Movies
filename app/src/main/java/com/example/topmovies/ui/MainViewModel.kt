package com.example.topmovies.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.topmovies.Resource
import com.example.topmovies.api.Api
import com.example.topmovies.api.ApiResults
import com.example.topmovies.data.Repository
import com.example.topmovies.data.Movie
import kotlinx.coroutines.Delay
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    //MutableLiveData String that stores the most recent response
    val response: MutableLiveData<List<Movie>> = MutableLiveData()


    fun getMovies() {
        var pageNum = 1
        var counter = 0
        viewModelScope.launch {
            while (counter < Api.Api_Num_Of_Results)
                try {
                    val listResult = repository.getMovies(pageNum)
                    val movie = listResult.results.map {
                        Movie(
                            name = it.originalTitle.toString(),
                            id = it.id,
                            overview = it.overview.toString(),
                            voteAvg = it.voteAverage,
                            image = it.posterPath
                        )
                    }
                    response.value = movie
                    counter += movie.size
                    pageNum++
                } catch (networkError: IOException) {
                    networkError.printStackTrace()
                }
        }
    }

    /*val movies: MutableLiveData<Resource<ApiResults>> = MutableLiveData()
    init {
        getMovies()
    }
    fun getMovies() = viewModelScope.launch {
        movies.postValue(Resource.Loading())
        val listResult = repository.getMovies(pageNum)
        movies.postValue(handleMoviesResponse(listResult))
    }

    private fun handleMoviesResponse(response: Response<ApiResults>): Resource<ApiResults> {
        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }
    */


}