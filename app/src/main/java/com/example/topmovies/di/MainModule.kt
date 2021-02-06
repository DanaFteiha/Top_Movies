package com.example.topmovies.di

import androidx.lifecycle.ViewModelProvider
import com.example.topmovies.ui.details.DetailsViewModelFactory
import com.example.topmovies.ui.movies.MovieViewModelFactory
import dagger.Binds
import dagger.Module
import javax.inject.Named

@Module
abstract class MainModule {
    @Binds
    @Named("movie")
    abstract fun bindMainViewModel(factory: MovieViewModelFactory): ViewModelProvider.Factory

    @Binds
    @Named("details")
    abstract fun bindDetailsViewModel(factory: DetailsViewModelFactory): ViewModelProvider.Factory
}