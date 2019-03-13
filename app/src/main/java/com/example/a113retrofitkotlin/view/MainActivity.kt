package com.example.a113retrofitkotlin.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.ViewModelProviders.*

import com.example.a113retrofitkotlin.R
import com.example.a113retrofitkotlin.databinding.ActivityMainBinding
import com.example.a113retrofitkotlin.model.WeatherDigital
import com.example.a113retrofitkotlin.viewmodel.WeatherViewModel
import com.example.recyclerview.remote.retrofit.ApiUtils
import com.example.recyclerview.remote.retrofit.WeatherService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Callback

import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
     lateinit var weatherViewModel: WeatherViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        weatherViewModel = ViewModelProviders.of(this@MainActivity).get(WeatherViewModel::class.java)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewmodel = WeatherViewModel(application)
        binding.lifecycleOwner = this
    }
}

