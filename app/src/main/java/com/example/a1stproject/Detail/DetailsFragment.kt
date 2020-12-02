package com.example.a1stproject.Detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.a1stproject.R
import com.example.a1stproject.SettingsManager
import com.example.a1stproject.changeToDegreeString


class DetailsFragment : Fragment() {
    val args:DetailsFragmentArgs by navArgs()
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
        val view = inflater.inflate(R.layout.details_fragment, container, false)

        savingSettingsManager = SettingsManager(requireContext())
        detailsLayoutTemp = view.findViewById(R.id.detailTemp)
        detailsLayoutDesc = view.findViewById(R.id.detailDescription)

        detailsLayoutTemp.text = changeToDegreeString(args.temp, savingSettingsManager.getPreference())
        detailsLayoutDesc.text = args.deck

        return  view
    }





 

}