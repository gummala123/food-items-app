package com.example.myapi

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapi.models.MealResponse
import com.example.myapi.retro.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvFood = findViewById<TextView>(R.id.tvFood)

        RetrofitClient.api.getMeals().enqueue(object : Callback<MealResponse> {

            override fun onResponse(
                call: Call<MealResponse>,
                response: Response<MealResponse>
            ) {
                if (response.isSuccessful) {
                    val meals = response.body()?.meals

                    val text = meals?.joinToString("\n\n") {
                        "Meal: ${it.strMeal}\n" +
                                "Category: ${it.strCategory}\n" +
                                "Area: ${it.strArea}"
                    }

                    tvFood.text = text
                }
            }

            override fun onFailure(call: Call<MealResponse>, t: Throwable) {
                tvFood.text = "Failed to load food data"
            }
        })
    }
}
