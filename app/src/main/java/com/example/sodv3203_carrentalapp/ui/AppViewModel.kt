package com.example.sodv3203_carrentalapp.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.sodv3203_carrentalapp.data.AppUiState
import com.example.sodv3203_carrentalapp.data.Car
import com.example.sodv3203_carrentalapp.data.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class AppViewModel : ViewModel() {

    // Define State
    private val _uiState = MutableStateFlow(AppUiState())
    val uiState : StateFlow<AppUiState> = _uiState.asStateFlow()

    // Define Custom Methods
    private fun updateLoggedUser(user: User?) {
        _uiState.update { currentState ->
            currentState.copy(
                loggedUser = user,
                isUserLoggedIn = user != null
            )
        }
    }

    fun authenticate(username: String, password: String): Boolean {
        for (user in uiState.value.listAllUsers) {
            if (username==user.username && password==user.password) {
                updateLoggedUser(user)
            }
        }
        return uiState.value.isUserLoggedIn
    }

    fun signout() {
        updateLoggedUser(null)
    }


    fun updateSelectedCar(car: Car) {
        Log.d("MyTag", "updateSelectedCar is being called")
        _uiState.update { currentState ->
            currentState.copy(
                selectedCar = car
            )
        }
    }

}