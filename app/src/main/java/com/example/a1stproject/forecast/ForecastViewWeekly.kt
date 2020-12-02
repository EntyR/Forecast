package com.example.a1stproject.forecast

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a1stproject.*
import com.example.a1stproject.api.WeeklyForecastModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ForecastViewWeekly : Fragment() {

    private val TAG = this.javaClass.simpleName

    private lateinit var savingSettingsManager: SettingsManager
    private val repository = WeatherCastRespository()

    lateinit var locationButton: FloatingActionButton
    lateinit var locationRepository: LocationRepository
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        locationRepository = LocationRepository(requireContext())






        val view = inflater.inflate(R.layout.fragment_forecast_view_weekly, container, false)
        val recycleView = view.findViewById<RecyclerView>(R.id.recyclerView)

        locationButton = view.findViewById(R.id.floatingActionButton)
        locationButton.setOnClickListener {

            val action = ForecastViewWeeklyDirections.actionForecastViewWeeklyToChooseRegion()
            findNavController().navigate(action)


        }
        savingSettingsManager = SettingsManager(requireContext())






        Log.e("!!!!!!!!!!!!", (recycleView==null).toString())
        recycleView.layoutManager = LinearLayoutManager(requireContext())
        val forecastAdapter = WeatherCastAdapter(savingSettingsManager.getPreference()){

           val action = ForecastViewWeeklyDirections.actionForecastViewWeeklyToDetailsFragment(it.temp.max, it.weather[0].description)
            findNavController().navigate(action)


        }
        recycleView.adapter = forecastAdapter


        val weathercastObserver = Observer<WeeklyForecastModel>{
            forecastAdapter.submitList(it.daily)

        }
        val locationObserver = Observer<Location> {
            val zipcode = it as Location.LocationData
            Log.i(TAG, "location observer")
            repository.loadForecastData(zipcode.zipcode)
        }
        locationRepository.savedLocation.observe(viewLifecycleOwner, locationObserver)
        repository.weeklyForecast.observe(viewLifecycleOwner, weathercastObserver)




        return  view
    }


    companion object {

        val ZIP_KEY = "zipcode_key"

        fun createInstance(zipcode: String): ForecastViewWeekly{
            val _bandle = Bundle()
            _bandle.putString(ZIP_KEY, zipcode)
            val viewInstance = ForecastViewWeekly()
            viewInstance.arguments = _bandle
            return viewInstance

        }

    }


}