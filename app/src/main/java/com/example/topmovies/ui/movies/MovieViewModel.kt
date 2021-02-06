package com.example.topmovies.ui.movies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.topmovies.data.Movie
import com.example.topmovies.ui.data.Repository
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    //MutableLiveData object that stores the most recent response
    val moviesResponse: MutableLiveData<List<Movie>> = MutableLiveData()
    var isLoading: MutableLiveData<Boolean> = MutableLiveData()

    //calling the getMovies function to make the network request
    init {
        getMovies()
    }

    //Before the network call was made a loading state was set to the mutable live data object
    private fun getMovies() = viewModelScope.launch {
        isLoading.value = true
        moviesResponse.value = repository.getMovies()
        isLoading.value = false
    }
}

