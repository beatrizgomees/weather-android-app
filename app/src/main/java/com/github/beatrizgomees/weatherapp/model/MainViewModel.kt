package com.github.beatrizgomees.weatherapp.model

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val cities = getCities().toMutableStateList()


    fun remove(city: City) {
        cities.remove(city)
    }
    fun add(city: String) {
        cities.add(City(city, "Carregando clima..."))
    }


}

private fun getCities() = List(30){
        i -> City(name = "CIdade $i", weather = "Carregando Clima...")
}

data class City(val name : String, var weather: String)