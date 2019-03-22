package com.example.a113retrofitkotlin.view

import android.os.Build
import android.os.Bundle
import android.transition.TransitionManager
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModelProviders
import com.example.a113retrofitkotlin.NetworkChangeReceiver
import com.example.a113retrofitkotlin.R
import com.example.a113retrofitkotlin.databinding.ActivityMainBinding
import com.example.a113retrofitkotlin.viewmodel.WeatherViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), LifecycleObserver {
    lateinit var weatherViewModel: WeatherViewModel

    var receiver: NetworkChangeReceiver = NetworkChangeReceiver()

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        weatherViewModel = ViewModelProviders.of(this@MainActivity).get(WeatherViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewmodel = weatherViewModel
        binding.lifecycleOwner = this

        btnrefresh.setOnClickListener {
            //            Toast.makeText(this, "refresh", Toast.LENGTH_LONG).show()
            weatherViewModel.loaddata()

        }
        var changed = false
        val constraintSet1 = ConstraintSet()
        constraintSet1.clone(constrain_layout)
        val constraintSet2 = ConstraintSet()
        constraintSet2.clone(this, R.layout.activity_main_screen2)




        name.setOnClickListener {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                TransitionManager.beginDelayedTransition(constrain_layout)
            }
            val constraint = if (changed) constraintSet1 else constraintSet2
            constraint.applyTo(constrain_layout)
            changed = !changed
        }
//
//        var filter = IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
//        registerReceiver(receiver, filter);
//    }
//
//    override fun onStop() {
//        super.onStop()
//        unregisterReceiver(receiver)
    }

//    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
//    fun loaddataonresume() {
//        Toast.makeText(this, "refresh", Toast.LENGTH_LONG).show()
//
//        if (weatherViewModel.livedataweather == null) {
//            weatherViewModel.loaddata()
//        }
//    }

}

