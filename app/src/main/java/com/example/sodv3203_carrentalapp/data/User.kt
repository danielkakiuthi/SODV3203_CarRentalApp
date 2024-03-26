package com.example.sodv3203_carrentalapp.data

data class User (
    val id: Int,
    val username: String,
    val password: String
)

data class RegisteredUser (
    val id: Int,
    val username: String,
    val password: String,
    val firstname: String,
    val lastname: String,
    val birthdate: String,
    val phone: String,
    val email: String
)