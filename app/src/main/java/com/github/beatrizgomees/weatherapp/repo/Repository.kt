package com.github.beatrizgomees.weatherapp.repo

import FBDatabase
import android.content.Context
import com.github.beatrizgomees.weatherapp.api.WeatherService
import com.github.beatrizgomees.weatherapp.local.LocalDB
import com.github.beatrizgomees.weatherapp.model.City
import com.github.beatrizgomees.weatherapp.model.Forecast
import com.github.beatrizgomees.weatherapp.model.User
import com.github.beatrizgomees.weatherapp.model.Weather
import com.google.android.gms.maps.model.LatLng

class Repository(context : Context, private var listener : Listener): FBDatabase.Listener {
    private var fbDb = FBDatabase (this)
    private var weatherService = WeatherService()
    private var localDB: LocalDB = LocalDB(context, databaseName = "local.db")
    interface Listener {
        fun onUserLoaded(user: User)
        fun onCityAdded(city: City)
        fun onCityRemoved(city: City)
        fun onCityUpdated(city: City)
        fun onUserSignOut()
    }

    init {
        localDB.getCities {
            fbDb.add(it)
        }
    }
    fun addCity(name: String) {
        weatherService.getLocation(name) { lat, lng ->
            val city = City(name = name, location = LatLng(lat?:0.0, lng?:0.0))
            localDB.insert(city)
            fbDb.add(city)
        }
    }
    fun addCityLatLong(lat: Double, lng: Double) {
        weatherService.getName(lat, lng) { name ->
            val  city = City( name = name?:"NOT_FOUND", location = LatLng(lat, lng))
            localDB.insert(city)
            fbDb.add(city)
        }
    }
    fun update(city: City) {
        localDB.update(city)
        fbDb.update(city)
    }

    fun remove(city: City) {
        localDB.delete(city)
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

    override fun onUserSignOut() {
        listener.onUserSignOut()
    }

    override fun onCityUpdated(city: City) {
        listener.onCityUpdated(city)
    }

    fun loadWeather(city: City) {
        weatherService.getCurrentWeather(city.name) { apiWeather ->
            city.weather = Weather (
                date = apiWeather?.current?.last_updated?:"...",
                desc = apiWeather?.current?.condition?.text?:"...",
                temp = apiWeather?.current?.temp_c?:-1.0,
                imgUrl = "https:" + apiWeather?.current?.condition?.icon
            )
            listener.onCityUpdated(city)
        }
    }

    fun loadForecast(city : City) {
        weatherService.getForecast(city.name) { result ->
            city.forecast = result?.forecast?.forecastday?.map {
                Forecast(
                    date = it.date?:"00-00-0000",
                    weather = it.day?.condition?.text?:"Erro carregando!",
                    tempMin = it.day?.mintemp_c?:-1.0,
                    tempMax = it.day?.maxtemp_c?:-1.0,
                    imgUrl = ("https:" + it.day?.condition?.icon)
                )
            }
            listener.onCityUpdated(city)
        }
    }

    fun loadBitmap(city: City) {
        weatherService.getBitmap(city.weather!!.imgUrl) { bitmap ->
            city.weather!!.bitmap = bitmap
            listener.onCityUpdated(city)
        }
    }

}