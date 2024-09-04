package com.github.beatrizgomees.weatherapp.api

data class APICurrentWeather(
    var APILocation: APILocation? = null,
    var current: APIWeather? = null
)