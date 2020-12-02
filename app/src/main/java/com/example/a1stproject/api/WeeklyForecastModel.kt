package com.example.a1stproject.api

import com.squareup.moshi.Json

data class WeatherDescription(val main:String, val description: String, val icon:String)
data class Temp(val min:Float, val max:Float)

data class DailyForecast(
    @field:Json(name = "dt") val date:Long,
    val temp: Temp,
    val weather: List<WeatherDescription>
)

data class WeeklyForecastModel(
    val daily: List<DailyForecast>
)