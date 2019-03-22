package com.example.recyclerview.remote.retrofit

object ApiUtils {

    val BASE_URL = "https://api.openweathermap.org/data/2.5/"
    val  soService: WeatherService
        get() = RetrofitClient.getClient(BASE_URL)!!.create(WeatherService::class.java)
}