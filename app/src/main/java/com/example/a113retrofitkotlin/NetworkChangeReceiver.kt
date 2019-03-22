package com.example.a113retrofitkotlin

import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.example.a113retrofitkotlin.viewmodel.WeatherViewModel

class NetworkChangeReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context, "network avaiable", Toast.LENGTH_LONG).show()
        Log.d("networkchange", ""+intent)
    }
}
