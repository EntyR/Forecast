package com.example.a1stproject.region

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.a1stproject.INavigation
import com.example.a1stproject.R


class ChooseRegion : Fragment() {

    lateinit var textView: TextView
    lateinit var button: Button
    lateinit var editText: EditText
    lateinit var mainActivity: INavigation



    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as INavigation



    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_choose_region, container, false)

        textView = view.findViewById(R.id.textView)
        button = view.findViewById(R.id.button)
        editText = view.findViewById(R.id.editTextTextPersonName)

        button.setOnClickListener {
            mainActivity.NavigateToDetail(editText.text.toString())
        }

        return  view
    }

    companion object {

    }
}