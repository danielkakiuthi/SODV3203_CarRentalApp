package com.example.sodv3203_carrentalapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cars")
data class Car(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var imageResourceId: Int,
    var name: String,
    var feature: String,
    var category: String,
    var seat: Int,
    var bags: Int,
    var doors: Int,
    var imageUrl: String = ""
)