package com.example.appclima.data.manager

import android.content.Context
import com.example.appclima.data.model.ForecastResponse
import com.example.appclima.data.model.WeatherResponse
import com.example.appclima.data.remote.api.WeatherAPI
import com.example.appclima.data.remote.client.ServiceGenerator
import com.example.appclima.util.SharedPreferencesConnector
import rx.Observable

class DataManager (val context: Context) {
    private val connector = SharedPreferencesConnector.getInstance(context)

    fun getWeather (lat: Double, lon: Double) : Observable<WeatherResponse> {
        return ServiceGenerator.createService(WeatherAPI::class.java, context).getWeather(lat.toString(),lon.toString(),"e2dd1bc1f45a59fd581e1f01044e6e31","metric")
    }

    fun getForecast():Observable<ForecastResponse>{
        return ServiceGenerator.createService(WeatherAPI::class.java, context).getForecast("524901","e2dd1bc1f45a59fd581e1f01044e6e31","metric")
    }
}