package com.example.topmovies.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "movie_table")
@Parcelize
data class Movie(
    val name: String?,
    @PrimaryKey val id: Int?,
    val overview:String?,
    val voteAvg: Float?,
    val image: String?
) :  Parcelable
