package com.example.a1stproject

fun changeToDegreeString(temp:Float, tempUnit: TempUnit):String{

    return when (tempUnit){
        TempUnit.Calcium -> {
            val celc = (temp - 32f) * (5f/9f)
            return String.format("%.2f C°", celc)
        }
        TempUnit.Fahrenheit -> {
            return String.format("%.2f F°", temp)
        }
    }

}