package com.github.beatrizgomees.weatherapp

import android.app.Application
import com.github.beatrizgomees.weatherapp.monitor.ForecastMonitor
import com.github.beatrizgomees.weatherapp.repo.Repository

class WeatherApp : Application() {
    override fun onCreate() {
        super.onCreate()
        val monitor = ForecastMonitor(this)
        val repo = Repository(monitor)
    }
}