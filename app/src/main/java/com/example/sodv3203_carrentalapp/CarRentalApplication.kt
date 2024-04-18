package com.example.sodv3203_carrentalapp

import android.app.Application
import com.example.sodv3203_carrentalapp.data.AppContainer
import com.example.sodv3203_carrentalapp.data.AppDataContainer

class CarRentalApplication : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}