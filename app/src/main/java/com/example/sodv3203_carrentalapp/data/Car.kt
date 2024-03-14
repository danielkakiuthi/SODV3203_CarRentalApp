package com.example.sodv3203_carrentalapp.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.sodv3203_carrentalapp.R

data class Car(
    @DrawableRes val imageResourceId: Int,
    @StringRes val name: Int,
    @StringRes val feature: Int,
    @StringRes val category: Int,
    val seat: Int,
    val bags: Int,
    val doors: Int,
)

val cars = listOf(
    Car(R.drawable.car01,R.string.car_name_1,R.string.car_feature_1,R.string.sedan,4,3,4 ),
    Car(R.drawable.car02,R.string.car_name_2,R.string.car_feature_2,R.string.sedan,4,4,4 ),
    Car(R.drawable.car03,R.string.car_name_3,R.string.car_feature_3,R.string.sedan,4,2,4 ),
    Car(R.drawable.car04,R.string.car_name_4,R.string.car_feature_4,R.string.suvs,5,5,4 ),
    Car(R.drawable.car05,R.string.car_name_5,R.string.car_feature_5,R.string.suvs,5,5,4 ),
    Car(R.drawable.car06,R.string.car_name_6,R.string.car_feature_6,R.string.suvs,5,5,4 ),
    Car(R.drawable.car07,R.string.car_name_7,R.string.car_feature_7,R.string.van,10,6,3 ),
    )
