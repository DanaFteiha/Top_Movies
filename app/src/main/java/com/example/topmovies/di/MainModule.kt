package com.example.topmovies.di

import androidx.lifecycle.ViewModelProvider
import com.example.topmovies.ui.MovieViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class MainModule {

    @Binds
    abstract fun bindMainViewModel(factory: MovieViewModelFactory): ViewModelProvider.Factory
}