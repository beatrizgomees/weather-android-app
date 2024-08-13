package com.github.beatrizgomees.weatherapp.model


import com.google.android.gms.maps.model.LatLng

data class City (
    val name: String,
    var weather: String,
    var location: LatLng? = null
)