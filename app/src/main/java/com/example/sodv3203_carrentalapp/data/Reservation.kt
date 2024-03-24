package com.example.sodv3203_carrentalapp.data

import java.util.Date

data class Reservation(
    val id: Int,
    val user: User,
    val car: Car,
    val location: String,
    val startDate: Date,
    val endDate: Date
)
