package com.example.appclima.data.remote.api

import com.example.appclima.data.model.ForecastResponse
import com.example.appclima.data.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

interface WeatherAPI {


    @GET("weather?")
    fun getWeather(@Query("lat") lat : String,
                   @Query("lon") lon : String,
                   @Query("appid") apikey : String,
                   @Query("units") units:String) : Observable<WeatherResponse>

    @GET("forecast?")
    fun getForecast(@Query("id") id : String,
                    @Query("appid") apikey: String,
                    @Query("units") units:String): Observable<ForecastResponse>

}