package com.example.topmovies.data

import androidx.lifecycle.MutableLiveData
import androidx.room.TypeConverter
import com.example.topmovies.api.ApiResults

class Converters {
    @TypeConverter
    fun convert(result: ApiResults): List<Movie> =
        result.results.map {
            Movie(
                name = it.originalTitle.toString(),
                id = it.id,
                overview = it.overview.toString(),
                voteAvg = it.voteAverage,
                image = it.posterPath
            )
        }
}