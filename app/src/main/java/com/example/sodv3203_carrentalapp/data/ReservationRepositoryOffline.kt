package com.example.sodv3203_carrentalapp.data

import kotlinx.coroutines.flow.Flow

class ReservationRepositoryOffline(private val reservationDao: ReservationDao) : ReservationRepository {

    override fun getAllReservationsStream(): Flow<List<Reservation>> = reservationDao.getAllReservations()
    override fun getReservationStream(id: Int): Flow<Reservation?> = reservationDao.getReservation(id)
    override suspend fun insertReservation(reservation: Reservation) = reservationDao.insert(reservation)
    override suspend fun deleteReservation(reservation: Reservation) = reservationDao.delete(reservation)
    override suspend fun updateReservation(reservation: Reservation) = reservationDao.update(reservation)

}