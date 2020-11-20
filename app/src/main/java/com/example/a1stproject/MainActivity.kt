package com.example.a1stproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a1stproject.Detail.DetailsActivity

class Location : AppCompatActivity() {
    lateinit var textView: TextView
    lateinit var button: Button
    lateinit var editText: EditText
    private lateinit var savingSettingsManager: SettingsManager
    private val repository = WeatherCastRespository()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        savingSettingsManager = SettingsManager(this)
        textView = findViewById(R.id.textView)
        button = findViewById(R.id.button)
        editText = findViewById(R.id.editTextTextPersonName)


        button.setOnClickListener {
            repository.loadForecastData("replace with real")
        }
        val recycleView = findViewById<RecyclerView>(R.id.recyclerView)
        recycleView.layoutManager = LinearLayoutManager(this)
        val forecastAdapter = WeatherCastAdapter(savingSettingsManager.getPreference()){
            val forecastIntent = Intent(this, DetailsActivity::class.java)
            forecastIntent.putExtra(DetailsActivity.TEMP_KEY, it.tempature)
            forecastIntent.putExtra(DetailsActivity.DESRIP_INFO, it.description)

            startActivity(forecastIntent)
        }
        recycleView.adapter = forecastAdapter


        val weathercastObserver = Observer<List<WeatherCast>>{
            forecastAdapter.submitList(it)
        }
        repository.weatherForecast.observe(this, weathercastObserver)

    }
}