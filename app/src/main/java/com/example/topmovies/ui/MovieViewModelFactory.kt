package com.example.topmovies.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

//Classes that extend viewModels can only have empty constructors
//So, we use viewModelFactory
class MovieViewModelFactory@Inject constructor(private val mainViewModelProvider: Provider<MovieViewModel>): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return mainViewModelProvider.get() as T
    }
}