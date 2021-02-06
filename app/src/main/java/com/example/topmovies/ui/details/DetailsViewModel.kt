package com.example.topmovies.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.topmovies.data.Movie
import com.example.topmovies.ui.data.Repository
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailsViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    //MutableLiveData object that stores the most recent response
    var movies: MutableLiveData<List<Movie>> = MutableLiveData()

    //Before the network call was made a loading state was set to the mutable live data object
    fun findMovieByID(movieID: Int) = viewModelScope.launch {
        movies.value = repository.getMoviesFromDatabase().filter { it.id == movieID }
    }
}