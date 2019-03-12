package com.example.a113retrofitkotlin.viewmodel

data class LivedataWeather(

    var name: String,
    var pressure: Int,
    var windr: Double?,
    var humidity: Int,
    var temperature: Double,
    var cloud: Int,
    var winspeed: Double
)
