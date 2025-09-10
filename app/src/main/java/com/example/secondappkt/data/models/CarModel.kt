package com.example.secondappkt.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import com.example.secondappkt.enums.Shape
import com.example.secondappkt.enums.Transmission
import com.example.secondappkt.enums.YesNo

@Parcelize
data class CarModel(
    val img: String,
    val title: String,
    val price: Int,
    val shape: Shape,
    val transmission: Transmission,
    val ac: YesNo
) : Parcelable