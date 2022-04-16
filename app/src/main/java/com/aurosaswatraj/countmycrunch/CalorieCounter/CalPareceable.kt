package com.aurosaswatraj.countmycrunch.CalorieCounter

import android.os.Parcel
import android.os.Parcelable

class CalPareceable(var disptitle: String?,var mfoodDisplay: String?,var swipeNext: String?) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),

    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(disptitle)
        parcel.writeString(mfoodDisplay)
        parcel.writeString(swipeNext)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CalPareceable> {
        override fun createFromParcel(parcel: Parcel): CalPareceable {
            return CalPareceable(parcel)
        }

        override fun newArray(size: Int): Array<CalPareceable?> {
            return arrayOfNulls(size)
        }
    }

}
