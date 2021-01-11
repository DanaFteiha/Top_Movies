package com.example.topmovies.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.topmovies.Resource
import com.example.topmovies.api.ApiResults
import com.example.topmovies.data.Repository
import com.example.topmovies.data.Movie
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

class MovieViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    //MutableLiveData object that stores the most recent response
    val moviesResponse: MutableLiveData<List<Movie>> = MutableLiveData()
    //add isloading
    private var pageNum = 1

    //calling the getMovies function to make the network request
    init {
        getMovies()
    }

    //The view model scope is used to launch our suspend function
    //The coroutine stays alive as long as the view model is alive
    //Before the network call was made a loading state was set to the mutable live data object
    private fun getMovies() = viewModelScope.launch {

        moviesResponse.value = repository.getMovies(pageNum)
    }

    //A function to handle the response got from network
    //Returns success if the response was successful and Error otherwise
    private fun handleMoviesResponse(response: Response<ApiResults>): Resource<ApiResults> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }
}

