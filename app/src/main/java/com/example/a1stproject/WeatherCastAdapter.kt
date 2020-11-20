package com.example.a1stproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class ViewHolder(view: View): RecyclerView.ViewHolder(view){
    val temp = view.findViewById<TextView>(R.id.itemTemp)
    val description = view.findViewById<TextView>(R.id.itemDescripton)


    fun bind(weatherCast:WeatherCast, tempUnit: TempUnit){
        temp.text = changeToDegreeString(weatherCast.tempature, tempUnit)
        description.text = weatherCast.description.toString()
    }
}

class WeatherCastAdapter(
    val tempUnit: TempUnit,
    private val  clickCallback: (WeatherCast) -> Unit
):ListAdapter<WeatherCast, ViewHolder>(DIF_CONFIG) {

    companion object{
        val DIF_CONFIG = object : DiffUtil.ItemCallback<WeatherCast>(){
            override fun areItemsTheSame(oldItem: WeatherCast, newItem: WeatherCast): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: WeatherCast, newItem: WeatherCast): Boolean {
               return oldItem == newItem
            }

        }
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.forecat_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(getItem(position), tempUnit)
        holder.itemView.setOnClickListener {
            clickCallback(getItem(position))
        }

    }
}
