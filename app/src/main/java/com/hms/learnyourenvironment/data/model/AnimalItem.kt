package com.hms.learnyourenvironment.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class AnimalItem(var animalName: String,
                      var animalImg: Int,
                      var animalDescription : String) : Parcelable
