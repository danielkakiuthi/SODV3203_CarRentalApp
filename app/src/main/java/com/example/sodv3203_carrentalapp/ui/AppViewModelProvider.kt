package com.example.sodv3203_carrentalapp.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.sodv3203_carrentalapp.CarRentalApplication

object AppViewModelProvider {

    val Factory = viewModelFactory {
        initializer {
            AppViewModel(
                carRentalApplication().container.appRepository
            )
        }
    }
}

fun CreationExtras.carRentalApplication(): CarRentalApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as CarRentalApplication)