package com.github.beatrizgomees.weatherapp

import android.app.Application
import androidx.compose.ui.platform.LocalContext
import com.github.beatrizgomees.weatherapp.monitor.ForecastMonitor
import com.github.beatrizgomees.weatherapp.repo.Repository

class WeatherApp : Application() {
    override fun onCreate() {
        super.onCreate()

        // Obtenha o contexto da aplicação
        val monitor = ForecastMonitor(this)

        // Use applicationContext em vez de context
        val repo = Repository(applicationContext, monitor)
    }
}
