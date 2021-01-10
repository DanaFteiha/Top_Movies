package com.example.topmovies.dependencyInjection

import androidx.lifecycle.ViewModelProvider
import com.example.topmovies.ui.MainViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class MainModule {

    @Binds
    abstract fun bindMainViewModel(factory: MainViewModelFactory): ViewModelProvider.Factory
}