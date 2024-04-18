package com.example.sodv3203_carrentalapp.ui

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.example.sodv3203_carrentalapp.data.Car
import com.example.sodv3203_carrentalapp.data.CarRepository
import com.example.sodv3203_carrentalapp.data.Reservation
import com.example.sodv3203_carrentalapp.data.ReservationRepository
import com.example.sodv3203_carrentalapp.data.User
import com.example.sodv3203_carrentalapp.data.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class AppViewModel(
    private val userRepository: UserRepository,
    private val carRepository: CarRepository,
    private val reservationRepository: ReservationRepository
    ) : ViewModel() {

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
    suspend fun addUserInDatabase(newUser: User) {
        Log.d("MyTag", "[ViewModel] addUserInDatabase is being called")
        if(validateInputNewUser(newUser)) {
            userRepository.insertUser(newUser)
            Log.d("MyTag", "[addUserInDatabase] New User Successfully added to users Table")
            Log.d("MyTag", "newUser.username added: ${newUser.username}")
        }
        else {
            Log.d("MyTag", "[addUserInDatabase] User not added to Database. Invalid Input for New User.")
        }
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


    private fun validateInputNewUser(user: User) : Boolean {
        return with(user) {
            username.isNotBlank()
            && password.isNotBlank()
            && firstName.isNotBlank()
            && lastName.isNotBlank()
            && birthDate.isNotBlank()
            && phone.isNotBlank()
            && email.isNotBlank()
        }
    }


    /* --------------------------------------------------------------------------------------------
    *  ------------------------------------- CARS -------------------------------------------------
    *  -------------------------------------------------------------------------------------------- */
    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun addCarInDatabase(newCar: Car) {
        Log.d("MyTag", "[ViewModel] addCarInDatabase is being called")
        if(validateInputNewCar(newCar)) {
            carRepository.insertCar(newCar)
            Log.d("MyTag", "[addCarInDatabase] New Car Successfully added to cars Table")
            Log.d("MyTag", "newCar.name added: ${newCar.name}")
        }
        else {
            Log.d("MyTag", "[addCarInDatabase] Car not added to Database. Invalid Input for New Car.")
        }
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


    private fun validateInputNewCar(car: Car) : Boolean {
        return with(car) {
            name.isNotBlank()
            && feature.isNotBlank()
            && category.isNotBlank()
        }
    }


    /* --------------------------------------------------------------------------------------------
    *  --------------------------------- RESERVATIONS ---------------------------------------------
    *  -------------------------------------------------------------------------------------------- */
    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun addReservationInDatabase(newReservation: Reservation) {
        Log.d("MyTag", "[ViewModel] addReservationInDatabase is being called")
        if(validateInputNewReservation(newReservation)) {
            reservationRepository.insertReservation(newReservation)
            Log.d("MyTag", "[addReservationInDatabase] New Reservation Successfully added to reservations Table")
            Log.d("MyTag", "newReservation.userId added: ${newReservation.userId}")
            Log.d("MyTag", "newReservation.carId added: ${newReservation.carId}")
        }
        else {
            Log.d("MyTag", "[addReservationInDatabase] Reservation not added to Database. Invalid Input for New Reservation.")
        }
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


    private fun validateInputNewReservation(reservation: Reservation) : Boolean {
        return with(reservation) {
            location.isNotBlank()
            && nameOnCard.isNotBlank()
            && cardNumber.isNotBlank()
            && cvc.isNotBlank()
        }
    }

}