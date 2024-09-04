package com.github.beatrizgomees.weatherapp.api

data class APIForecast (
    var forecastday: List<APIForecastDay>? = null
)