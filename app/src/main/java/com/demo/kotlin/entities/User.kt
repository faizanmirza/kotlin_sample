package com.demo.kotlin.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    @SerializedName("first_name") val firstName: String,
    @SerializedName("last_name") val lastName: String,
    @SerializedName("email") val email: String,
    @SerializedName("avatar") val profilePic: String
) : Parcelable {

    fun getName(): String {
        return "$firstName $lastName"
    }
}
