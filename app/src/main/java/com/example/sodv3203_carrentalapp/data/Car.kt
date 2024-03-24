package com.example.sodv3203_carrentalapp.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Car(
    @DrawableRes val imageResourceId: Int,
    @StringRes val name: Int,
    @StringRes val feature: Int,
    @StringRes val category: Int,
    val seat: Int,
    val bags: Int,
    val doors: Int,
)
