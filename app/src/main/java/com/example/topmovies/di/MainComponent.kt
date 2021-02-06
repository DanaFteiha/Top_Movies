package com.example.topmovies.di

import com.example.topmovies.ui.Fragments.MoviesFragment
import dagger.Component
import javax.inject.Singleton

//The Injector which tells dagger which classes to inject
@Singleton
@Component(modules = [MainModule::class, ApiModule::class, AppModule::class, DatabaseModule::class])
interface MainComponent {
    // This tells Dagger that MoviesFragment requests injection so the graph needs to
    // satisfy all the dependencies of the fields it is requesting.
    fun inject(activity: MoviesFragment)
}