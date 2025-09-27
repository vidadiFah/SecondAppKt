package com.example.secondappkt.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import com.example.secondappkt.core.enums.Shape
import com.example.secondappkt.core.enums.Transmission
import com.example.secondappkt.core.enums.YesNo

@Parcelize
data class CarModel(
    val img: String,
    val title: String,
    val price: Int,
    val shape: Shape,
    val transmission: Transmission,
    val ac: YesNo
) : Parcelable