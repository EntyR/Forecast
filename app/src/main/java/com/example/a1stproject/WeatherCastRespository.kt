package com.example.a1stproject

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.a1stproject.api.DailyForecastModel
import com.example.a1stproject.api.WeeklyForecastModel
import com.example.a1stproject.api.createOpenWeatherService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherCastRespository {
    private val _weeklyForecast = MutableLiveData<WeeklyForecastModel>()
    val weeklyForecast = _weeklyForecast

    private val _currentForecast = MutableLiveData<DailyForecastModel>()
    val currentForecast = _currentForecast

    fun loadForecastData(zipcode: String) {
        val retrofit = createOpenWeatherService().currentWeather("${zipcode},ru", "metric", BuildConfig.OPEN_WEATHER_API_KEY)
        retrofit.enqueue(object : Callback<DailyForecastModel> {
            override fun onFailure(call: Call<DailyForecastModel>, t: Throwable) {
                Log.e("Weather repository", "unable enqueue weather request")
            }

            override fun onResponse(
                call: Call<DailyForecastModel>,
                response: Response<DailyForecastModel>
            ) {

                val weather = response.body()
                Log.e("Responce", response.message())
                if (weather != null) {
                    val weeklyforecastCall = createOpenWeatherService().weeklyWeather(
                        weather.coord.lat,
                        weather.coord.lon,
                        "current,minutely,hourly",
                        "metric",
                        BuildConfig.OPEN_WEATHER_API_KEY
                    )
                    weeklyforecastCall.enqueue(object : Callback<WeeklyForecastModel> {
                        override fun onResponse(
                            call: Call<WeeklyForecastModel>,
                            response: Response<WeeklyForecastModel>) {
                            Log.e("Responce", response.message())
                            if (response.body() != null) {
                                _weeklyForecast.value = response.body()
                            }

                        }

                        override fun onFailure(call: Call<WeeklyForecastModel>, t: Throwable) {
                            Log.e("Weather repository", "unable enqueue weather request")
                        }

                    })
                }

            }
        })
    }

    fun loadCurrentForecast(zipcode:String){
        val retrofit = createOpenWeatherService().currentWeather("${zipcode},ru", "metric", BuildConfig.OPEN_WEATHER_API_KEY)
        retrofit.enqueue(object : Callback<DailyForecastModel>{
            override fun onFailure(call: Call<DailyForecastModel>, t: Throwable) {
                Log.e("Weather repository", "unable enqueue weather request")
            }

            override fun onResponse(call: Call<DailyForecastModel>, response: Response<DailyForecastModel>) {

                val  weather = response.body()
                Log.e("Responce", response.message())
                if (weather!=null){
                    Log.e("Weather class", weather.foreCast.temp.toString())
                    _currentForecast.value = weather
                }
            }


        })

    }
}
