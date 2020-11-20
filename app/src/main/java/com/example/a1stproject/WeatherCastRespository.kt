package com.example.a1stproject

import androidx.lifecycle.MutableLiveData
import kotlin.random.Random

class WeatherCastRespository {
    private val _weeklyForecast = MutableLiveData<List<WeatherCast>>()
    val weatherForecast = _weeklyForecast

    fun loadForecastData(zipcode: String) {
        val value = List(11) {
            Random.nextFloat().rem(100f) * 100

        }
        val forecastString = value.map {
            WeatherCast(it, "It's always cold here huh")
        }
        _weeklyForecast.setValue(forecastString)
    }
}