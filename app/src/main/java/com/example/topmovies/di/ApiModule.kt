package com.example.topmovies.di

import com.example.topmovies.api.Api
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


//https://api.themoviedb.org/3/movie/top_rated?api_key=2bce1ad0d3d0586476f1b9ed1701b22a
@Module
class ApiModule {
    // @Provides tell Dagger how to create instances of the type that this function
    //Retrofit object "Singleton"
    @Singleton
    @Provides
    fun provideRetrofitService(): Api {

        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder()
            .baseUrl(Api.BASE_URL).client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        //A public Api object that exposes the lazy-initialized Retrofit service
        return retrofit.create(Api::class.java)
    }
}