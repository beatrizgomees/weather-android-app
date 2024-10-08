package com.github.beatrizgomees.weatherapp.local

import android.content.Context
import androidx.room.Room
import com.github.beatrizgomees.weatherapp.model.City
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LocalDB (context : Context, databaseName : String) {
    private var roomDB : LocalCityDatabase = Room.databaseBuilder(
        context = context,
        klass = LocalCityDatabase::class.java,
        name = databaseName
    ).build()
    private var scope : CoroutineScope = CoroutineScope(Dispatchers.IO)
    fun insert(city: City) =  scope.launch {
        city.toLocalCity()?.let { roomDB.favCityDao().upsert(it) }
    }
    fun update(city: City) = scope.launch {
        city.toLocalCity()?.let { roomDB.favCityDao().upsert(it) }
    }
    fun delete(city: City) =  scope.launch {
        city.toLocalCity()?.let { roomDB.favCityDao().delete(it) }
    }
    fun getCities(doSomething : (City) -> Unit) = scope.launch {
        roomDB.favCityDao().getCities().collect { list ->
            val mappedList = list.map { it.toCity() }
            mappedList.forEach { doSomething(it) }
        }
    }
}