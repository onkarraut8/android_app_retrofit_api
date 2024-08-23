package com.example.retrofitapi

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiInterface {

    @GET("/posts")
    fun getData():Call<List<UserData>>

    @POST("/posts")
    fun addData(@Body userData: UserData) :Call<UserData>

    @PUT("/posts/{id}")
    fun updateData(@Path("id") id: Int, @Body userData: UserData) :Call<UserData>

    @DELETE("/posts/{id}")
    fun deleteData(@Path("id") id: Int) :Call<UserData>
}