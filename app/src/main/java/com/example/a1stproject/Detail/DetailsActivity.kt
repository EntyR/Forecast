package com.example.a1stproject.Detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.a1stproject.R
import com.example.a1stproject.SettingsManager
import com.example.a1stproject.changeToDegreeString


class DetailsActivity : Fragment() {
    lateinit var savingSettingsManager : SettingsManager
    companion object {
        const val TEMP_KEY = "temp_info"
        const val DESRIP_INFO = "desc_info"
    }

    lateinit var detailsLayoutTemp:TextView
    lateinit var detailsLayoutDesc:TextView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_details, container)

        savingSettingsManager = SettingsManager(requireContext())
        detailsLayoutTemp = view.findViewById(R.id.detailTemp)
        detailsLayoutDesc = view.findViewById(R.id.detailDescription)
        detailsLayoutTemp.text = changeToDegreeString( 75f, savingSettingsManager.getPreference())
        detailsLayoutDesc.text = "Cold"

        return  view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }



 

}