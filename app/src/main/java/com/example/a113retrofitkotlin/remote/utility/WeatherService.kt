package com.example.recyclerview.remote.retrofit

import com.example.a113retrofitkotlin.model.WeatherDigital

import retrofit2.Call
import retrofit2.http.GET

//quan ly viec truy xuat voi server
interface WeatherService {

//    @GET("weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22")
//    @GET("weather?zip=94040,us&appid=3c95226d7ea54d8cb670090e3a3cafde")
    @GET("weather?id=1581130&appid=3c95226d7ea54d8cb670090e3a3cafde")
    fun weatherCall() : Call<WeatherDigital>

}