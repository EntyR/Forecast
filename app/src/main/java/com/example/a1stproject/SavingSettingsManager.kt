package com.example.a1stproject

import android.content.Context
import android.content.SharedPreferences

enum class TempUnit{
    Calcium, Fahrenheit
}



class SettingsManager(context: Context){

    private val preferenceSetting: SharedPreferences = context.getSharedPreferences("setting", Context.MODE_PRIVATE)

    companion object{
        const val UNIT = "temp_unit"
    }

    fun getPreference(): TempUnit {

        val unitSetting : String = preferenceSetting.getString(UNIT, TempUnit.Calcium.name)
            ?: "Calcium"
        return TempUnit.valueOf(unitSetting)
    }
    fun setPreference(tempUnit: TempUnit):Unit{
        preferenceSetting.edit().putString(UNIT, tempUnit.name).commit()
    }
}