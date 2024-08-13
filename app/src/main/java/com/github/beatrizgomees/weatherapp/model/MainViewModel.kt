package com.github.beatrizgomees.weatherapp.model

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng

class MainViewModel : ViewModel() {
    private val _cities = getCities().toMutableStateList()
    val cities : List<City>

    get() = _cities



    fun remove(city: City) {
        _cities.remove(city)
    }
    fun add(city: String, location: LatLng? = null) {
        _cities.add(City(city, "Carregando clima...", location))
    }


}

private fun getCities() = List(30){
        i -> City(name = "CIdade $i", weather = "Carregando Clima...")
}

data class City(val name : String, var weather: String, var location: LatLng? = null)