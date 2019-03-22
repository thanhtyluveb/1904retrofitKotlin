package com.example.a113retrofitkotlin.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.a113retrofitkotlin.model.WeatherDigital
import com.example.a113retrofitkotlin.remote.utility.database.WeatherModel
import com.example.a113retrofitkotlin.repository.WeatherRepository
import com.example.recyclerview.remote.retrofit.ApiUtils
import com.example.recyclerview.remote.retrofit.WeatherService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.logging.Handler

open class WeatherViewModel(application: Application) : AndroidViewModel(application) {
    var livedataweather: MutableLiveData<WeatherModel> = MutableLiveData()
    var dataweather: LiveData<WeatherModel>
    var weatherService: WeatherService
    var reposity: WeatherRepository


    //    constructor(application: Application) : super(application) {
//        reposity = WeatherRepository(application)
//        dataweather = reposity.data
//        weatherService = ApiUtils.soService
//        loaddata()
//    }
    init {
        reposity = WeatherRepository(application)
        dataweather = reposity.data
        weatherService = ApiUtils.soService
        loaddata()

    }

    fun loaddata() {
        weatherService!!.weatherCall().enqueue(object : Callback<WeatherDigital> {
            override fun onResponse(call: Call<WeatherDigital>, response: Response<WeatherDigital>) {
                if (response.isSuccessful) {
                    var apiresults: WeatherDigital = response.body()!!
                    var weathermodelresult = WeatherModel(
                        apiresults.name!!,
                        Math.round(apiresults.main!!.temp!! - 275.0).toDouble(),
                        apiresults.main!!.pressure!!,
                        apiresults.main!!.humidity!!,
                        apiresults.clouds!!.all!!,
                        apiresults.wind!!.speed!!,
                        apiresults.wind!!.deg!!
                    )

                    livedataweather.value = weathermodelresult
                    reposity.insert(weathermodelresult)
                    Toast.makeText(getApplication(), "online mode", Toast.LENGTH_LONG).show()

                }
            }

            override fun onFailure(call: Call<WeatherDigital>, t: Throwable) {
                dataweather.observeForever(
                    Observer {
                        livedataweather.value = dataweather.value
                        Toast.makeText(getApplication(), "offline mode", Toast.LENGTH_LONG).show()
//                        dataweather.removeObserver { livedataweather }

                    })



            }

        })

    }

}


