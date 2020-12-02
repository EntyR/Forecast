package com.example.a1stproject

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var savingSettingsManager: SettingsManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        savingSettingsManager = SettingsManager(this)

        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(navController.graph)

        findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar).setupWithNavController(navController, appBarConfiguration)
        findViewById<BottomNavigationView>(R.id.bottom_navigation).setupWithNavController(navController)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val _menuInflater = menuInflater
        _menuInflater.inflate(R.menu.forecast_menu, menu)
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.changeUnitItem -> {
                showTempSettingDialog()
                true
            }
            else -> false
        }


    }
    private fun showTempSettingDialog(){
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle("Unit Settings")
        dialogBuilder.setMessage("Choose which unit you prefer for your forecast")
        dialogBuilder.setPositiveButton("C°"){ _, _ ->
            savingSettingsManager.setPreference(TempUnit.Calcium)

        }
        dialogBuilder.setNeutralButton("F°"){ _, _ ->
            savingSettingsManager.setPreference(TempUnit.Fahrenheit)

        }
        dialogBuilder.show()
    }


}