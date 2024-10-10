package com.example.appclima.data.model

import android.os.Parcel
import android.os.Parcelable


data class Coordenadas (
        val lon : Double,
        var lat : Double
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readDouble(),
        parcel.readDouble()
    ) {
        }
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeDouble(lon)
        parcel.writeDouble(lat)
    }

    override fun describeContents(): Int {
        return 0
    }

        companion object CREATOR : Parcelable.Creator<Coordenadas> {
            override fun createFromParcel(parcel: Parcel): Coordenadas {
                return Coordenadas(parcel)
            }

            override fun newArray(size: Int): Array<Coordenadas?> {
                return arrayOfNulls(size)
            }
        }
    }