package com.example.retrofitapi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiUtilities {

    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    private fun getinstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getPlacesApi(): ApiInterface {
        return getinstance()
            .create(ApiInterface::class.java)
    }


}