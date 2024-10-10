package com.example.appclima.data.model

import android.os.Parcel
import android.os.Parcelable

class MainWeather (private val temp: Double,
                   val feels_like: Double,
                   val temp_min : Double,
                   val temp_max : Double,
                   val pressure : Double,
                   val humidity : Double) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeDouble(temp)
        parcel.writeDouble(feels_like)
        parcel.writeDouble(temp_min)
        parcel.writeDouble(temp_max)
        parcel.writeDouble(pressure)
        parcel.writeDouble(humidity)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MainWeather> {
        override fun createFromParcel(parcel: Parcel): MainWeather {
            return MainWeather(parcel)
        }

        override fun newArray(size: Int): Array<MainWeather?> {
            return arrayOfNulls(size)
        }
    }
}