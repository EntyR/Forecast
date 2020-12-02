package com.example.a1stproject

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

private val KEY_ZIPCODE = "key_zipcode"
sealed class Location{
    data class LocationData(val zipcode:String): Location()
}

class LocationRepository(context: Context) {
    private val TAG = this.javaClass.simpleName
    val setting = context.getSharedPreferences("settings", Context.MODE_PRIVATE)
    private val _savedLocation: MutableLiveData<Location> = MutableLiveData()
    val savedLocation: LiveData<Location> = _savedLocation

    init{
        setting.registerOnSharedPreferenceChangeListener{ sharedPreferences, key ->
            Log.e(TAG, "preference observer !complite")
            if (key != KEY_ZIPCODE) return@registerOnSharedPreferenceChangeListener
            checkPreference()
            Log.i(TAG, "preference observer complite")
        }
        checkPreference()
    }

    fun saveLocation(location: Location){
        when(location){
             is Location.LocationData -> {
                 setting.edit().putString(KEY_ZIPCODE, location.zipcode).apply()
                 Log.i(TAG, "Zipcode Setted")
             }

        }

    }
    private fun checkPreference() {
        val zipcode = setting.getString(KEY_ZIPCODE, "")
        if (zipcode != null && zipcode.isNotBlank()) {
            _savedLocation.value = Location.LocationData(zipcode)
        }

    }

}