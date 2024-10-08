package com.github.beatrizgomees.weatherapp.repo

import FBDatabase
import com.github.beatrizgomees.weatherapp.api.WeatherService
import com.github.beatrizgomees.weatherapp.model.City
import com.github.beatrizgomees.weatherapp.model.Forecast
import com.github.beatrizgomees.weatherapp.model.User
import com.github.beatrizgomees.weatherapp.model.Weather
import com.google.android.gms.maps.model.LatLng

class Repository(private var listener : Listener): FBDatabase.Listener {
    private var fbDb = FBDatabase (this)
    private var weatherService = WeatherService()

    interface Listener {
        fun onUserLoaded(user: User)
        fun onCityAdded(city: City)
        fun onCityRemoved(city: City)
        fun onCityUpdated(city: City)
        fun onUserSignOut()
    }
    fun addCity(name: String) {
        weatherService.getLocation(name) { lat, lng ->
            fbDb.add(City(name = name,
                location = LatLng(lat?:0.0, lng?:0.0)))
        }
    }
    fun addCityLatLong(lat: Double, lng: Double) {
        weatherService.getName(lat, lng) { name ->
            fbDb.add( City( name = name?:"NOT_FOUND",
                location = LatLng(lat, lng)))
        }
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

    override fun onUserSignOut() {
        listener.onUserSignOut()
    }

    override fun onCityUpdated(city: City) {
        listener.onCityUpdated(city)
    }
    fun update(city: City) {
        fbDb.update(city)
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