package com.github.beatrizgomees.weatherapp.api

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.squareup.picasso.Target;

class WeatherService {
    private var weatherAPI: WeatherServiceAPI
    init {
        val retrofitAPI = Retrofit.Builder()
            .baseUrl(WeatherServiceAPI.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
        weatherAPI = retrofitAPI.create(WeatherServiceAPI::class.java)
    }


    fun getName(lat: Double, lng: Double, onResponse : (String?) -> Unit ) {
        search("$lat,$lng") { loc -> onResponse (loc?.name) }
    }


    fun getLocation(name: String, onResponse: (lat:Double?, long:Double?) -> Unit) {
        search(name) { loc -> onResponse (loc?.lat, loc?.lon) }
    }

    private fun search(query: String, onResponse : (APILocation?) -> Unit) {
        val call: Call<List<APILocation>?> = weatherAPI.search(query)
        enqueue(call = call) {
            if (it != null) {
                onResponse.invoke(it[0])
            }
        }
    }

    fun getCurrentWeather(name: String, onResponse: (APICurrentWeather?) -> Unit){
        val call: Call<APICurrentWeather?> = weatherAPI.currentWeather(name)
        enqueue(call) { onResponse.invoke(it) }
    }

    fun getForecast(name: String, onResponse : (APIWeatherForecast?) -> Unit) {
        val call: Call<APIWeatherForecast?> = weatherAPI.forecast(name)
        enqueue(call) { onResponse.invoke(it) }
    }
    private fun <T> enqueue(call: Call<T?>, onResponse: ((T?) -> Unit) ? = null) {
        call.enqueue(object : Callback<T?> {
            override fun onResponse(call: Call<T?>, response: Response<T?>) {
                val obj: T? = response.body()
                onResponse?.invoke(obj)
            }

            override fun onFailure(call: Call<T?>, t: Throwable) {
                Log.w("Weather WARNING", "" + t.message)
            }
        })
    }
    private var picassoTarget: Target? = null
    fun getBitmap(imgUrl: String, onResponse: (Bitmap?) -> Unit) {
        Picasso.get().load(imgUrl).into(object : com.squareup.picasso.Target {
            override fun onBitmapLoaded(bitmap: Bitmap?,
                                        from: Picasso.LoadedFrom?) {
                onResponse.invoke(bitmap)
            }
            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {}
            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                Log.w("WeatherApp WARNING", "" + e?.message)
                e?.printStackTrace()
            }
        })
    }
}