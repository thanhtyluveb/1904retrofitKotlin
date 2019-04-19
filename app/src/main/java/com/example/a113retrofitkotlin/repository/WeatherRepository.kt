package com.example.a113retrofitkotlin.repository

import android.app.Application
import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.a113retrofitkotlin.remote.utility.database.WeatherDao
import com.example.a113retrofitkotlin.remote.utility.database.WeatherDatabase
import com.example.a113retrofitkotlin.remote.utility.database.WeatherModel

class WeatherRepository(application: Application) {
    private val weatherDao: WeatherDao
    var weatherModel: LiveData<WeatherModel>

    val data: LiveData<WeatherModel>
        get() {
            getdataAsynctask().execute()
            return weatherModel
        }


    init {
        val db = WeatherDatabase.getDatabase(application)
        weatherDao = db!!.weatherDao()
        weatherModel = weatherDao.getdata()

    }


    fun insert(weatherModel: WeatherModel) {
        insertAsyncTask(weatherDao).execute(weatherModel)
    }

    private class insertAsyncTask(internal var aSyncweatherDao: WeatherDao) : AsyncTask<WeatherModel, Void, Void>() {

        override fun doInBackground(vararg weatherModels: WeatherModel): Void? {
            aSyncweatherDao.deleteAll()
            aSyncweatherDao.insert(weatherModels[0])
            Log.d("insertok", "ok")
            return null

        }
    }


    inner class getdataAsynctask() : AsyncTask<Void, Void, LiveData<WeatherModel>>() {
        override fun doInBackground(vararg voids: Void): LiveData<WeatherModel> {
            return weatherDao.getdata()

        }

        override fun onPostExecute(result: LiveData<WeatherModel>?) {
            super.onPostExecute(result)
        }

    }
}



