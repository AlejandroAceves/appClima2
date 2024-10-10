package com.example.appclima.ui

import com.example.appclima.data.model.Forecast
import com.example.appclima.data.model.ForecastMain

interface MainContract {

    interface  View{
        fun getInitialData(lat:Double,lon: Double)
        fun showWeather(climate : String)
        fun showItemsData(items:ArrayList<Forecast>)
        fun showDialogInfo(forecast: ForecastMain)
    }

    interface  Presenter{
        fun getWeather(lat: Double, lon: Double)
        fun generateItemsData()
        fun triggerDialogInfo(forecast: Forecast)
    }
}