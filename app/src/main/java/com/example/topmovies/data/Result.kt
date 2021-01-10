package com.example.topmovies.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("id")
    @Expose
    var id: Int? = null,
    @SerializedName("vote_average")
    @Expose
    var voteAverage: Float? = null,
    @SerializedName("TITLE")
    @Expose
    var title: String? = null,
    @SerializedName("poster_path")
    @Expose
    var posterPath: String? = null,
    @SerializedName("overview")
    @Expose
    var overview: String? = null,
    @SerializedName("original_title")
    @Expose
    var originalTitle: String? = null,
    @SerializedName("release_date")
    @Expose
    var releaseDate: String? = null
)