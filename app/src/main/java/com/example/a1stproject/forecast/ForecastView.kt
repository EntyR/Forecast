package com.example.a1stproject.forecast

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a1stproject.*
import com.example.a1stproject.Detail.DetailsActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ForecastView : Fragment() {

    private lateinit var savingSettingsManager: SettingsManager
    private val repository = WeatherCastRespository()
    lateinit var mainActivity: INavigation
    lateinit var locationButton: FloatingActionButton
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as INavigation
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {





        val view = inflater.inflate(R.layout.fragment_forecast_view, container, false)

        locationButton = view.findViewById(R.id.floatingActionButton)
        locationButton.setOnClickListener {
            mainActivity.NavigateToSelect()
        }
        savingSettingsManager = SettingsManager(requireContext())

        val zipcode = "11111"//arguments!!.getString(ZIP_KEY) ?: "11111"



        val recycleView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recycleView.layoutManager = LinearLayoutManager(requireContext())
        val forecastAdapter = WeatherCastAdapter(savingSettingsManager.getPreference()){
            val forecastIntent = Intent(requireContext(), DetailsActivity::class.java)
            forecastIntent.putExtra(DetailsActivity.TEMP_KEY, it.tempature)
            forecastIntent.putExtra(DetailsActivity.DESRIP_INFO, it.description)

            startActivity(forecastIntent)
        }
        recycleView.adapter = forecastAdapter


        val weathercastObserver = Observer<List<WeatherCast>>{
            forecastAdapter.submitList(it)
        }
        repository.weatherForecast.observe(viewLifecycleOwner, weathercastObserver)

        repository.loadForecastData(zipcode)


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