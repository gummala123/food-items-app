package com.example.myapi.retro

import com.example.myapi.models.MealResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("search.php?s=")
    fun getMeals(): Call<MealResponse>
}