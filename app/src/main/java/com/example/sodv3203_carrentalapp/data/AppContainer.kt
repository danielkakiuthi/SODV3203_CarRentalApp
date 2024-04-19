package com.example.sodv3203_carrentalapp.data

import android.content.Context


interface AppContainer {
    val appRepository: AppRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val appRepository: AppRepository by lazy {
        AppRepositoryOffline(AppDatabase.getInstance(context).appDao())
    }
}