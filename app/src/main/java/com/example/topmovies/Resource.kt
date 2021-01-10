package com.example.topmovies

//To differentiate between successful and error responses
//Also handles progress bar\
//Sealed class to limit the classes that are allowed to inherit from recourse
sealed class Resource<T>(
    val response: T? = null,
    val message: String? = null
) {
    class Success<T>(response: T) : Resource<T>(response)
    class Error<T>(message: String, response: T? = null) : Resource<T>(response, message)
    class Loading<T> : Resource<T>()
}