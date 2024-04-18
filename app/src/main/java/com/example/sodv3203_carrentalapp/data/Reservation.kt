package com.example.sodv3203_carrentalapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import java.text.Format
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Entity(tableName = "reservations")
data class Reservation(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var userId: Int,
    var carId: Int,
    var location: String,
    var startDate: Date,
    var endDate: Date,
    var pricePerDay: Float,
    var additionalRequests: String,
    var nameOnCard: String,
    var cardNumber: String,
    var cvc: String
)