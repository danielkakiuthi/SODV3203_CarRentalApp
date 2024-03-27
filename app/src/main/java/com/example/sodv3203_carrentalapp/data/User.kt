package com.example.sodv3203_carrentalapp.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue


class User (
    var id: Int,
    username: String,
    password :String,
    firstName :String,
    lastName :String,
    birthDate :String,
    phone :String,
    email :String,
) {
    var username by mutableStateOf(username);
    var password by mutableStateOf(password)
    var firstName by mutableStateOf(firstName)
    var lastName by mutableStateOf(lastName)
    var birthDate by mutableStateOf(birthDate)
    var phone by mutableStateOf(phone)
    var email by mutableStateOf(email)
}
