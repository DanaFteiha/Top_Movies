package com.example.topmovies.di

import com.example.topmovies.api.Api
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiModule {
    // @Provides tell Dagger how to create instances of the type that this function
    //Retrofit object "Singleton"
    @Singleton
    @Provides
    fun provideRetrofitService(): Api {

        val interceptor = HttpLoggingInterceptor()
        interceptor.apply { interceptor.level = HttpLoggingInterceptor.Level.BODY }
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder()
            .baseUrl(Api.BASE_URL).client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        //A public Api object that exposes the Retrofit service
        return retrofit.create(Api::class.java)
    }
}