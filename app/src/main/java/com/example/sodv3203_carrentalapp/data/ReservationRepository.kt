package com.example.sodv3203_carrentalapp.data

import kotlinx.coroutines.flow.Flow

interface ReservationRepository {
    fun getAllReservationsStream(): Flow<List<Reservation>>
    fun getReservationStream(id: Int): Flow<Reservation?>
    suspend fun insertReservation(reservation: Reservation)
    suspend fun deleteReservation(reservation: Reservation)
    suspend fun updateReservation(reservation: Reservation)
}