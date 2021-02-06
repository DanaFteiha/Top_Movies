package com.example.topmovies.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class DetailsViewModelFactory @Inject constructor(private val mainViewModelProvider: Provider<DetailsViewModel>) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return mainViewModelProvider.get() as T
    }
}