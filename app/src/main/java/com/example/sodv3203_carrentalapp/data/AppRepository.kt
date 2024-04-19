package com.example.sodv3203_carrentalapp.data

import kotlinx.coroutines.flow.Flow

interface AppRepository {

    /* ----------------------------------------------------------------------
     * ------------------------- Car Repository -----------------------------
     * ---------------------------------------------------------------------- */
    fun getAllCarsStream(): Flow<List<Car>>
    fun getCarStream(id: Int): Flow<Car?>
    suspend fun insertCar(car: Car)
    suspend fun insertListCars(listCars: List<Car>)
    suspend fun deleteCar(car: Car)
    suspend fun updateCar(car: Car)


    /* ----------------------------------------------------------------------
     * ------------------------ User Repository -----------------------------
     * ---------------------------------------------------------------------- */
    fun getAllUsersStream(): Flow<List<User>>
    fun getUserStream(id: Int): Flow<User?>
    suspend fun insertUser(user: User)
    suspend fun insertListUsers(listUsers: List<User>)
    suspend fun deleteUser(user: User)
    suspend fun updateUser(user: User)


    /* ----------------------------------------------------------------------
     * --------------------- Reservation Repository -------------------------
     * ---------------------------------------------------------------------- */
    fun getAllReservationsStream(): Flow<List<Reservation>>
    fun getReservationStream(id: Int): Flow<Reservation?>
    suspend fun insertReservation(reservation: Reservation)
    suspend fun insertListReservations(listReservations: List<Reservation>)
    suspend fun deleteReservation(reservation: Reservation)
    suspend fun updateReservation(reservation: Reservation)

}