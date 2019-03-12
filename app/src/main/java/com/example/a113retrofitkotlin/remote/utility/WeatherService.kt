package com.example.recyclerview.remote.retrofit

import com.example.a113retrofitkotlin.model.WeatherDigital

import retrofit2.Call
import retrofit2.http.GET

//quan ly viec truy xuat voi server
interface WeatherService {

    @GET("weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22")
    fun weatherCall() : Call<WeatherDigital>

}