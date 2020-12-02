package com.example.a1stproject.api

import com.squareup.moshi.Json

data class Coordinates(val lat : Float, val lon: Float)
data class Forecast(val temp: Float)

data class DailyForecastModel(
    val name:String,
    val coord:Coordinates,
    @field:Json(name = "main") val foreCast:Forecast

)