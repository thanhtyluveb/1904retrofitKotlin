package com.example.a113retrofitkotlin.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModelProviders
import com.example.a113retrofitkotlin.R
import com.example.a113retrofitkotlin.databinding.ActivityMainBinding
import com.example.a113retrofitkotlin.viewmodel.WeatherViewModel

class MainActivity : AppCompatActivity(), LifecycleObserver {
    lateinit var weatherViewModel: WeatherViewModel


    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        weatherViewModel = ViewModelProviders.of(this@MainActivity).get(WeatherViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewmodel = weatherViewModel
        binding.lifecycleOwner = this


//
    }


}

