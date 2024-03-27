package com.example.sodv3203_carrentalapp.data

import android.icu.text.SimpleDateFormat
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import java.util.Date

class Reservation(
    val id: Int,
    user: User,
    car: Car,
    location: String,
    startDate: Date,
    endDate: Date,
    pricePerDay: Float,
    additionalRequests: String,
    nameOnCard: String,
    cardNumber: String,
    cvc: String
) {
    var user by mutableStateOf(user)
    var car by mutableStateOf(car)
    var location by mutableStateOf(location)
    var startDate by mutableStateOf(startDate)
    var endDate by mutableStateOf(endDate)
    var pricePerDay by mutableStateOf(pricePerDay)
    var additionalRequests by mutableStateOf(additionalRequests)
    var nameOnCard by mutableStateOf(nameOnCard)
    var cardNumber by mutableStateOf(cardNumber)
    var cvc by mutableStateOf(cvc)
}
