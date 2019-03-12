package com.example.a113retrofitkotlin.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.a113retrofitkotlin.model.WeatherDigital
import com.example.recyclerview.remote.retrofit.ApiUtils
import com.example.recyclerview.remote.retrofit.WeatherService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherViewModel : ViewModel {
    var livedataweather: MutableLiveData<LivedataWeather> = MutableLiveData()
    private var weatherService: WeatherService? = null


    constructor() {
        livedataweather.value = LivedataWeather("", 0, 0.0, 0, 0.0, 0, 0.0)
    }


    init {
        weatherService = ApiUtils.soService
        loaddata()
    }

    fun loaddata() {

        weatherService!!.weatherCall().enqueue(object : Callback<WeatherDigital> {
            override fun onFailure(call: Call<WeatherDigital>, t: Throwable) {
                Log.d("AAAA", "fail")

            }

            override fun onResponse(call: Call<WeatherDigital>, response: Response<WeatherDigital>) {
                if (response.isSuccessful)
                    Log.d("AAAA", "ok")
                var apiresults: WeatherDigital = response.body()!!

                livedataweather.value = LivedataWeather(
                    apiresults.name!!,
                    apiresults.main!!.pressure!!,
                    apiresults.wind!!.speed,
                    apiresults.main!!.humidity!!,
                    apiresults.main!!.temp!!,
                    apiresults.clouds!!.all!!,
                    apiresults.wind!!.speed!!
                )


            }

        })

    }

}


