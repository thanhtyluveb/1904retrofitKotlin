package com.example.a113retrofitkotlin.remote.utility.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WeatherDao {
    @Insert
    fun insert(weatherModel: WeatherModel)

    @Query("DELETE FROM weather_table")
    fun deleteAll()

    @Query("SELECT * from weather_table")
    fun getdata(): LiveData<WeatherModel>
}
