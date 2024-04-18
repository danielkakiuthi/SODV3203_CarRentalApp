package com.example.sodv3203_carrentalapp.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sodv3203_carrentalapp.data.AppRepository
import com.example.sodv3203_carrentalapp.data.Car
import com.example.sodv3203_carrentalapp.data.Reservation
import com.example.sodv3203_carrentalapp.data.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update


class AppViewModel(
    private val appRepository: AppRepository
    ) : ViewModel() {

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    // Define State
    private val _uiState = MutableStateFlow(AppUiState())
    val uiState : StateFlow<AppUiState> = _uiState.asStateFlow()

    val listAllCars: StateFlow<List<Car>> = appRepository.getAllCarsStream().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
        initialValue = uiState.value.initialValue_ListAllCars
    )
    val listAllUsers: StateFlow<List<User>> = appRepository.getAllUsersStream().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
        initialValue = uiState.value.initialValue_ListAllUsers
    )
    val listAllReservations: StateFlow<List<Reservation>> = appRepository.getAllReservationsStream().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
        initialValue = uiState.value.initialValue_ListAllReservations
    )


    // Define Custom Methods

    /* --------------------------------------------------------------------------------------------
    *  ------------------------------------ USERS -------------------------------------------------
    *  -------------------------------------------------------------------------------------------- */
    fun updateLoggedUser(updateUser: User?) {
        Log.d("MyTag", "[ViewModel] updateLoggedUser is being called")
        _uiState.update { currentState ->
            currentState.copy(
                loggedUser = updateUser,
                isUserLoggedIn = updateUser != null
            )
        }
    }

    fun authenticate(username: String, password: String): User? {
        Log.d("MyTag", "[ViewModel] authenticate is being called")
        for (user in listAllUsers.value) {
            if (username==user.username && password==user.password) {
                updateLoggedUser(user)
                return user
            }
        }
        return null
    }

    fun signout() {
        Log.d("MyTag", "[ViewModel] signout is being called")
        updateLoggedUser(null)
    }


    suspend fun addUserInDatabase(newUser: User) {
        Log.d("MyTag", "[ViewModel] addUserInDatabase is being called")
        if(validateInputNewUser(newUser)) {
            appRepository.insertUser(newUser)
            Log.d("MyTag", "[addUserInDatabase] New User Successfully added to users Table")
            Log.d("MyTag", "newUser.username added: ${newUser.username}")
        }
        else {
            Log.d("MyTag", "[addUserInDatabase] User not added to Database. Invalid Input for New User.")
        }
    }


    suspend fun updateUserInDatabase(updateUser: User) {
        Log.d("MyTag", "[ViewModel] updateUserInDatabase is being called")

        appRepository.updateUser(updateUser)
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
    suspend fun addCarInDatabase(newCar: Car) {
        Log.d("MyTag", "[ViewModel] addCarInDatabase is being called")
        if(validateInputNewCar(newCar)) {
            appRepository.insertCar(newCar)
            Log.d("MyTag", "[addCarInDatabase] New Car Successfully added to cars Table")
            Log.d("MyTag", "newCar.name added: ${newCar.name}")
        }
        else {
            Log.d("MyTag", "[addCarInDatabase] Car not added to Database. Invalid Input for New Car.")
        }
    }


    fun updateSelectedCar(car: Car) {
        Log.d("MyTag", "[ViewModel] updateSelectedCar is being called")
        _uiState.update { currentState ->
            currentState.copy(
                selectedCar = car
            )
        }
    }

    fun updateSelectedCarByCarId(carId: Int) {
        Log.d("MyTag", "[ViewModel] updateSelectedCarByReservationId is being called")
        for (car in listAllCars.value) {
            if(carId == car.id) {
                _uiState.update { currentState ->
                    currentState.copy(
                        selectedCar = car
                    )
                }
                break
            }
        }
    }

    fun getCarById(carId: Int) : Car {
        for(car in listAllCars.value) {
            if(carId == car.id) {
                return car
            }
        }
        return uiState.value.placeholderCar
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
    suspend fun addReservationInDatabase(newReservation: Reservation) {
        Log.d("MyTag", "[ViewModel] addReservationInDatabase is being called")
        if(validateInputNewReservation(newReservation)) {
            appRepository.insertReservation(newReservation)
            Log.d("MyTag", "[addReservationInDatabase] New Reservation Successfully added to reservations Table")
            Log.d("MyTag", "newReservation.userId added: ${newReservation.userId}")
            Log.d("MyTag", "newReservation.carId added: ${newReservation.carId}")
        }
        else {
            Log.d("MyTag", "[addReservationInDatabase] Reservation not added to Database. Invalid Input for New Reservation.")
        }
    }


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