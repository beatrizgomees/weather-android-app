package com.github.beatrizgomees.weatherapp.repo

import FBDatabase
import com.github.beatrizgomees.weatherapp.model.City
import com.github.beatrizgomees.weatherapp.model.User
import com.google.android.gms.maps.model.LatLng

class Repository(private var listener : FBDatabase.Listener): FBDatabase.Listener {
    private var fbDb = FBDatabase (this)

    interface Listener {
        fun onUserLoaded(user: User)
        fun onCityAdded(city: City)
        fun onCityRemoved(city: City)
    }
    fun addCity(name: String) {
        fbDb.add(City(name, LatLng(0.0, 0.0)))
    }
    fun addCityLatLong(lat: Double, lng: Double) {
        fbDb.add(City("Cidade@$lat:$lng", LatLng(lat,lng)))
    }
    fun remove(city: City) {
        fbDb.remove(city)
    }
    fun register(userName: String, email: String) {
        fbDb.register(User(userName, email))
    }
    override fun onUserLoaded(user: User) {
        listener.onUserLoaded(user)
    }
    override fun onCityAdded(city: City) {
        listener.onCityAdded(city)
    }
    override fun onCityRemoved(city: City) {
        listener.onCityRemoved(city)
    }
}