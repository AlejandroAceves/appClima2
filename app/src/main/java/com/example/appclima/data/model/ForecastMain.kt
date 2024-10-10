package com.example.appclima.data.model

import android.os.Parcel
import android.os.Parcelable


class ForecastMain(
    val temp : Double,
    val feels_like : Double,
    val temp_min: Double,
    val temp_max: Double,
    val pressure: Int,
    val humidity: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeDouble(temp)
        parcel.writeDouble(feels_like)
        parcel.writeDouble(temp_min)
        parcel.writeDouble(temp_max)
        parcel.writeInt(pressure)
        parcel.writeInt(humidity)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ForecastMain> {
        override fun createFromParcel(parcel: Parcel): ForecastMain {
            return ForecastMain(parcel)
        }

        override fun newArray(size: Int): Array<ForecastMain?> {
            return arrayOfNulls(size)
        }
    }
}