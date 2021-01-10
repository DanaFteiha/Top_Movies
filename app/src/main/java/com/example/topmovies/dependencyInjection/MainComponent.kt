package com.example.topmovies.dependencyInjection

import com.example.topmovies.Room.AppModule
import com.example.topmovies.Room.DataBaseModule
import com.example.topmovies.Room.MovieDataBase
import com.example.topmovies.ui.Fragments.MoviesFragment
import dagger.Component
import javax.inject.Singleton

//The Injector
@Singleton
@Component (modules = [MainModule::class , ApiModule::class])
interface MainComponent {
    // This tells Dagger that MainActivity requests injection so the graph needs to
    // satisfy all the dependencies of the fields that MainActivity is requesting.
    fun inject (activity: MoviesFragment)
}