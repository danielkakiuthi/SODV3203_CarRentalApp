package com.example.sodv3203_carrentalapp.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Car(
    val id: Int,
    @DrawableRes val imageResourceId: Int,
    val name: String,
    val feature: String,
    val category: String,
    val seat: Int,
    val bags: Int,
    val doors: Int,
)
