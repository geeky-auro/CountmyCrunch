package com.aurosaswatraj.countmycrunch.ProgressTracking

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.math.BigDecimal

@Parcelize
data class Track(val gender: String, val age: Int,val height:String, val Weight:String,val amt_Consumed:BigDecimal,val required_Consumed:BigDecimal, val date:String,val sortorder: Int,var id: Long = 0):Parcelable {
}