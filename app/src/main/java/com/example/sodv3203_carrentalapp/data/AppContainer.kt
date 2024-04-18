package com.example.sodv3203_carrentalapp.data

import android.content.Context


interface AppContainer {
    val userRepository: UserRepository
    val carRepository: CarRepository
    val reservationRepository: ReservationRepository

}

class AppDataContainer(private val context: Context) : AppContainer {

    override val userRepository: UserRepository by lazy {
        UserRepositoryOffline(AppDatabase.getDatabase(context).userDao())
    }

    override val carRepository: CarRepository by lazy {
        CarRepositoryOffline(AppDatabase.getDatabase(context).carDao())
    }

    override val reservationRepository: ReservationRepository by lazy {
        ReservationRepositoryOffline(AppDatabase.getDatabase(context).reservationDao())
    }
}