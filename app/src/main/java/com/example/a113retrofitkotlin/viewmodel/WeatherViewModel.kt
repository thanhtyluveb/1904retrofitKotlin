package com.example.a113retrofitkotlin.viewmodel

import android.app.Application
import android.util.Log
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

class WeatherViewModel(application: Application) : AndroidViewModel(application) {

    var livedataweather: MutableLiveData<WeatherModel> = MutableLiveData()
    var dataweather: LiveData<WeatherModel>
    private var weatherService: WeatherService? = null
    var reposity: WeatherRepository

    init {
        reposity = WeatherRepository(application)
        livedataweather.value = WeatherModel("name1", 0.0, 0, 0, 0, 0.0, 0)
        dataweather = reposity.data
        weatherService = ApiUtils.soService
        loaddata()

    }


    fun loaddata() {

        weatherService!!.weatherCall().enqueue(object : Callback<WeatherDigital> {
            override fun onFailure(call: Call<WeatherDigital>, t: Throwable) {
                Log.d("AAAA", "fail")

                dataweather.observeForever(Observer {
                    livedataweather.value = dataweather.value
                    Toast.makeText(getApplication(), "dsds", Toast.LENGTH_LONG).show()
                })


            }

            override fun onResponse(call: Call<WeatherDigital>, response: Response<WeatherDigital>) {
                if (response.isSuccessful) {
                    Log.d("AAAA", "ok")
                    var apiresults: WeatherDigital = response.body()!!

                    var weathermodelresult = WeatherModel(
                        apiresults.name!!,
                        apiresults.main!!.temp!!,
                        apiresults.main!!.pressure!!,
                        apiresults.main!!.humidity!!,
                        apiresults.clouds!!.all!!,
                        apiresults.wind!!.speed!!,
                        apiresults.wind!!.deg!!
                    )

                    livedataweather.value = weathermodelresult
                    reposity.insert(weathermodelresult)
                }
            }

        })

    }

}


