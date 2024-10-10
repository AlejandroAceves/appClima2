package com.example.appclima.ui

import android.content.Context
import com.example.appclima.data.manager.DataManager
import com.example.appclima.data.model.Forecast
import com.example.appclima.data.model.ForecastResponse
import com.example.appclima.data.model.WeatherResponse
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MainPresenter (
    private val view: MainContract.View,
    private val context: Context
) : MainContract.Presenter {

    private val dataManager = DataManager(context)

    override fun getWeather(lat: Double, lon: Double) {
        val observable = dataManager.getWeather(lat, lon)
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<WeatherResponse>() {

                override fun onError(e: Throwable?) {
                    e?.printStackTrace()
                }

                override fun onNext(t: WeatherResponse) {

                    view.showWeather(t.main!!.feels_like.toString())
                }

                override fun onCompleted() {
                    // NA
                }
            })
    }

    override fun generateItemsData() {
        val observable = dataManager.getForecast()
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(object:Subscriber<ForecastResponse>(){
                override fun onError (e: Throwable?){
                    e?.printStackTrace()
                }

                override fun onNext(t:ForecastResponse){
                    view.showItemsData(t.list!!)
                }

                override fun onCompleted(){

                }
            })
    }


    override fun triggerDialogInfo(forecast: Forecast) {
        view.showDialogInfo(forecast.main!!)
    }
}