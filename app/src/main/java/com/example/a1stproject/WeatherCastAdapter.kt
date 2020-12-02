package com.example.a1stproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.a1stproject.api.DailyForecast
import java.text.SimpleDateFormat
import java.util.*

private  val DATE_FORMAT = SimpleDateFormat("dd-MM-yyyy")
class ViewHolder(view: View): RecyclerView.ViewHolder(view){
    val temp = view.findViewById<TextView>(R.id.itemTemp)
    val description = view.findViewById<TextView>(R.id.itemDescripton)
    val date = view.findViewById<TextView>(R.id.forecast_date)
    val image = view.findViewById<ImageView>(R.id.forecast_icon_item)


    fun bind(weatherCast:DailyForecast, tempUnit: TempUnit){
        temp.text = changeToDegreeString(weatherCast.temp.max, tempUnit)
        description.text = weatherCast.weather[0].description
        date.text = DATE_FORMAT.format(Date(weatherCast.date * 1000))
        image.load("http://openweathermap.org/img/wn/${weatherCast.weather[0].icon}@2x.png")
    }
}

class WeatherCastAdapter(
    val tempUnit: TempUnit,
    private val  clickCallback: (DailyForecast) -> Unit
):ListAdapter<DailyForecast, ViewHolder>(DIF_CONFIG) {

    companion object{
        val DIF_CONFIG = object : DiffUtil.ItemCallback<DailyForecast>(){
            override fun areItemsTheSame(oldItem: DailyForecast, newItem: DailyForecast): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: DailyForecast, newItem: DailyForecast): Boolean {
               return oldItem == newItem
            }

        }
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.forecast_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(getItem(position), tempUnit)
        holder.itemView.setOnClickListener {
            clickCallback(getItem(position))
        }

    }
}
