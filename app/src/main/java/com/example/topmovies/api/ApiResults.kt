package com.example.topmovies.api

import com.example.topmovies.data.Result
//The response of the API request is stored in the result list
data class ApiResults(
    val results: List<Result>
)