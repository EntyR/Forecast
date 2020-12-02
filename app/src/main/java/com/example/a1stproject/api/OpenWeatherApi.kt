package com.example.a1stproject.api

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


    fun createOpenWeatherService(): OpenWeatherApi{
        val retrofit = Retrofit.Builder()
            .baseUrl("http://api.openweathermap.org")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
       return retrofit.create(OpenWeatherApi::class.java)
    }




interface OpenWeatherApi{

    @GET("/data/2.5/weather")
    fun currentWeather(
        @Query(value = "zip") zipcode:String,
        @Query(value = "units") units:String,
        @Query(value =  "appid") appid:String
    ):Call<DailyForecastModel>
    @GET("/data/2.5/onecall")
    fun weeklyWeather(
        @Query(value = "lat") lat:Float,
        @Query(value = "lon") lon:Float,
        @Query(value = "exclude") exclude:String,
        @Query(value = "units") units:String,
        @Query(value =  "appid") appid:String

    ):Call<WeeklyForecastModel>
}