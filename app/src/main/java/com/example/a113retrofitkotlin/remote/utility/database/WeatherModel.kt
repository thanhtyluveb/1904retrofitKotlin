package com.example.a113retrofitkotlin.remote.utility.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather_table")
data class WeatherModel(
    @PrimaryKey
    var name: String,
    var temp: Double,
    var pressure: Int,
    var humidity: Int,
    var cloud: Int,
    var winsp: Double,
    var windr: Int


)
