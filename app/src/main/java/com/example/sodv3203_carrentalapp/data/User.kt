package com.example.sodv3203_carrentalapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val username: String,
    val password :String,
    val firstName :String,
    val lastName :String,
    val birthDate :String,
    val phone :String,
    val email :String,
)
