package com.example.appclima.data.model

import android.os.Parcel
import android.os.Parcelable
import java.util.ArrayList


class ForecastResponse(
    val cod: String?,
    val message: Int?,
    val cnt: Int?,
    val list: ArrayList<Forecast>?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        TODO("list")
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(cod)
        parcel.writeValue(message)
        parcel.writeValue(cnt)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ForecastResponse> {
        override fun createFromParcel(parcel: Parcel): ForecastResponse {
            return ForecastResponse(parcel)
        }

        override fun newArray(size: Int): Array<ForecastResponse?> {
            return arrayOfNulls(size)
        }
    }


}
