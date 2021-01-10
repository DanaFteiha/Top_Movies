package com.example.topmovies.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class MainViewModelFactory@Inject constructor(private val mainViewModelProvider: Provider<MainViewModel>): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return mainViewModelProvider.get() as T
    }
}