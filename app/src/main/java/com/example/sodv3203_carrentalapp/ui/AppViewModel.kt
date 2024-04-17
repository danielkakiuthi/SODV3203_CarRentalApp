package com.example.sodv3203_carrentalapp.ui

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.example.sodv3203_carrentalapp.data.AppUiState
import com.example.sodv3203_carrentalapp.data.Car
import com.example.sodv3203_carrentalapp.data.Reservation
import com.example.sodv3203_carrentalapp.data.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class AppViewModel : ViewModel() {

    // Define State
    @RequiresApi(Build.VERSION_CODES.O)
    private val _uiState = MutableStateFlow(AppUiState())
    @RequiresApi(Build.VERSION_CODES.O)
    val uiState : StateFlow<AppUiState> = _uiState.asStateFlow()

    // Define Custom Methods

    /* --------------------------------------------------------------------------------------------
    *  ------------------------------------ USERS -------------------------------------------------
    *  -------------------------------------------------------------------------------------------- */
    @RequiresApi(Build.VERSION_CODES.O)
    private fun updateLoggedUser(updateUser: User?) {
        Log.d("MyTag", "[ViewModel] updateLoggedUser is being called")
        _uiState.update { currentState ->
            currentState.copy(
                loggedUser = updateUser,
                isUserLoggedIn = updateUser != null
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun authenticate(username: String, password: String): Boolean {
        Log.d("MyTag", "[ViewModel] authenticate is being called")
        for (user in uiState.value.listAllUsers) {
            if (username==user.username && password==user.password) {
                updateLoggedUser(user)
            }
        }
        return uiState.value.isUserLoggedIn
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun signout() {
        Log.d("MyTag", "[ViewModel] signout is being called")
        updateLoggedUser(null)
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun addUserInDatabase(newUser: User) {
        Log.d("MyTag", "[ViewModel] addUserInDatabase is being called")
        _uiState.update { currentState ->
            currentState.copy(
                listAllUsers = _uiState.value.listAllUsers.plus(newUser)
            )
        }
        Log.d("MyTag", "listAllUsers length: ${_uiState.value.listAllUsers.size}")
        Log.d("MyTag", "newUser.Id added: ${_uiState.value.listAllUsers.last().id}")
        Log.d("MyTag", "newUser.username added: ${_uiState.value.listAllUsers.last().username}")
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun updateUserInDatabase(updateUser: User) {
        Log.d("MyTag", "[ViewModel] updateUserInDatabase is being called")
        var tempList: MutableList<User> = _uiState.value.listAllUsers.toMutableList()
        tempList.forEach() {
            if(it.id==updateUser.id) {
                tempList.remove(it)
            }
        }
        _uiState.update {currentState ->
            currentState.copy(
                listAllUsers = tempList.plus(updateUser)
            )
        }
        updateLoggedUser(updateUser)
    }


    /* --------------------------------------------------------------------------------------------
    *  ------------------------------------- CARS -------------------------------------------------
    *  -------------------------------------------------------------------------------------------- */
    @RequiresApi(Build.VERSION_CODES.O)
    fun addCarInDatabase(newCar: Car) {
        Log.d("MyTag", "[ViewModel] addCarInDatabase is being called")
        _uiState.update { currentState ->
            currentState.copy(
                listAllRegisteredCars = _uiState.value.listAllRegisteredCars.plus(newCar)
            )
        }
        Log.d("MyTag", "listAllRegisteredCars length: ${_uiState.value.listAllRegisteredCars.size}")
        Log.d("MyTag", "newCar.Id added: ${_uiState.value.listAllRegisteredCars.last().id}")
        Log.d("MyTag", "newCar.name added: ${_uiState.value.listAllRegisteredCars.last().name}")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun updateSelectedCar(updateCar: Car) {
        Log.d("MyTag", "[ViewModel] updateSelectedCar is being called")
        _uiState.update { currentState ->
            currentState.copy(
                selectedCar = updateCar
            )
        }
    }


    /* --------------------------------------------------------------------------------------------
    *  --------------------------------- RESERVATIONS ---------------------------------------------
    *  -------------------------------------------------------------------------------------------- */
    @RequiresApi(Build.VERSION_CODES.O)
    fun addReservationInDatabase(newReservation: Reservation) {
        Log.d("MyTag", "[ViewModel] addReservationInDatabase is being called")
        _uiState.update { currentState ->
            currentState.copy(
                listAllReservations = _uiState.value.listAllReservations.plus(newReservation)
            )
        }
        Log.d("MyTag", "listAllReservations length: ${_uiState.value.listAllReservations.size}")
        Log.d("MyTag", "newReservation.id added: ${_uiState.value.listAllReservations.last().id}")
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun updateSelectedReservation(updateReservation: Reservation) {
        Log.d("MyTag", "[ViewModel] updateSelectedReservation is being called")
        _uiState.update { currentState ->
            currentState.copy(
                selectedReservation = updateReservation
            )
        }
    }

}