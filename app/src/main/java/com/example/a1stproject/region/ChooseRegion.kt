package com.example.a1stproject.region

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.a1stproject.Location
import com.example.a1stproject.LocationRepository
import com.example.a1stproject.R


class ChooseRegion : Fragment() {

    lateinit var textView: TextView
    lateinit var button: Button
    lateinit var editText: EditText
    lateinit var locationRepository: LocationRepository
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        locationRepository = LocationRepository(requireContext())
        val view = inflater.inflate(R.layout.fragment_choose_region, container, false)

        textView = view.findViewById(R.id.textView)
        button = view.findViewById(R.id.button)
        editText = view.findViewById(R.id.editTextTextPersonName)

        button.setOnClickListener {
            Log.e("Choose region", "Btn clicked")
            if (editText.text.toString().length == 6){
                locationRepository.saveLocation(Location.LocationData(editText.text.toString()))
                findNavController().navigateUp()
            }
            else{

            }


        }

        return  view
    }


}