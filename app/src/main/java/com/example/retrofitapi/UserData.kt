package com.example.retrofitapi

import java.io.Serializable

data class UserData(

    val id: Int,
    val title: String,
    val body: String,
    val userId: Int
): Serializable