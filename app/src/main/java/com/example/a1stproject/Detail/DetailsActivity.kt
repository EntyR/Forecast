package com.example.a1stproject.Detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.a1stproject.R
import com.example.a1stproject.SettingsManager
import com.example.a1stproject.TempUnit
import com.example.a1stproject.changeToDegreeString


class DetailsActivity : AppCompatActivity() {
    lateinit var savingSettingsManager : SettingsManager
    companion object {
        const val TEMP_KEY = "temp_info"
        const val DESRIP_INFO = "desc_info"
    }

    lateinit var detailsLayoutTemp:TextView
    lateinit var detailsLayoutDesc:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        savingSettingsManager = SettingsManager(this)
        detailsLayoutTemp = findViewById(R.id.detailTemp)
        detailsLayoutDesc = findViewById(R.id.detailDescription)


      val extraTemp:Float = intent.getFloatExtra(TEMP_KEY, 73.00f)
      val extraDesc:String = intent.getStringExtra(DESRIP_INFO)?: "Cloudy"

      detailsLayoutTemp.text = changeToDegreeString(extraTemp, savingSettingsManager.getPreference())
      detailsLayoutDesc.text = extraDesc



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean{
        val inflater = menuInflater
        inflater.inflate(R.menu.forecast_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.changeUnitItem -> {
                showTempSettingDialog()
                return true
            }
            else -> super.onOptionsItemSelected(item)
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