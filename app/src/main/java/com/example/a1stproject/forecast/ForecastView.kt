package com.example.a1stproject.forecast

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.a1stproject.*
import com.example.a1stproject.api.DailyForecastModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ForecastView : Fragment() {

    private lateinit var savingSettingsManager: SettingsManager
    private val repository = WeatherCastRespository()
    lateinit var tempeture: TextView

    lateinit var locationButton: FloatingActionButton
    lateinit var location: TextView
    lateinit var locationRepository: LocationRepository
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_forecast_view, container, false)

        tempeture = view.findViewById(R.id.tempeture)
        location = view.findViewById(R.id.location)
        locationRepository = LocationRepository(requireContext())
        locationButton = view.findViewById(R.id.floatingActionButton)
        locationButton.setOnClickListener {
            val action = ForecastViewDirections.actionForecastViewToChooseRegion()
            findNavController().navigate(action)
        }

        val locationObserver = Observer<Location> {
            Log.e("ForecastView", "Location observer")
            val zipcode = it as Location.LocationData
            repository.loadCurrentForecast(zipcode.zipcode)
        }
        savingSettingsManager = SettingsManager(requireContext())







        val currentWeatherObserver = Observer<DailyForecastModel>{
            Log.e("ForecastView", "Forecast observer")
            location.text = it.name
            tempeture.text = changeToDegreeString(it.foreCast.temp, TempUnit.Fahrenheit)
        }
        repository.currentForecast.observe(viewLifecycleOwner, currentWeatherObserver)


        locationRepository.savedLocation.observe(viewLifecycleOwner, locationObserver)


        return  view
    }

    companion object {

        val ZIP_KEY = "zipcode_key"

        fun createInstance(zipcode: String): ForecastView{
            val _bandle = Bundle()
            _bandle.putString(ZIP_KEY, zipcode)
            val viewInstance = ForecastView()
            viewInstance.arguments = _bandle
            return viewInstance

        }

    }


}