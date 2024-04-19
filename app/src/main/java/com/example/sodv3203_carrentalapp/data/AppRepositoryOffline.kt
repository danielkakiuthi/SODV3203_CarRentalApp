package com.example.sodv3203_carrentalapp.data

import kotlinx.coroutines.flow.Flow

class AppRepositoryOffline(private val appDao: AppDao) : AppRepository {

    /* ----------------------------------------------------------------------
     * --------------------- Car Repository Offline -------------------------
     * ---------------------------------------------------------------------- */
    override fun getAllCarsStream(): Flow<List<Car>> = appDao.getAllCars()
    override fun getCarStream(id: Int): Flow<Car?> = appDao.getCar(id)
    override suspend fun insertCar(car: Car) = appDao.insert(car)
    override suspend fun insertListCars(listCars: List<Car>) = appDao.insertListCars(listCars)
    override suspend fun deleteCar(car: Car) = appDao.delete(car)
    override suspend fun updateCar(car: Car) = appDao.update(car)

    /* ----------------------------------------------------------------------
     * --------------------- User Repository Offline ------------------------
     * ---------------------------------------------------------------------- */
    override fun getAllUsersStream(): Flow<List<User>> = appDao.getAllUsers()
    override fun getUserStream(id: Int): Flow<User?> = appDao.getUser(id)
    override suspend fun insertUser(user: User) = appDao.insert(user)
    override suspend fun insertListUsers(listUsers: List<User>) = appDao.insertListUsers(listUsers)
    override suspend fun deleteUser(user: User) = appDao.delete(user)
    override suspend fun updateUser(user: User) = appDao.update(user)


    /* ----------------------------------------------------------------------
     * ----------------- Reservation Repository Offline ---------------------
     * ---------------------------------------------------------------------- */
    override fun getAllReservationsStream(): Flow<List<Reservation>> = appDao.getAllReservations()
    override fun getReservationStream(id: Int): Flow<Reservation?> = appDao.getReservation(id)
    override suspend fun insertReservation(reservation: Reservation) = appDao.insert(reservation)
    override suspend fun insertListReservations(listReservations: List<Reservation>) = appDao.insertListReservations(listReservations)
    override suspend fun deleteReservation(reservation: Reservation) = appDao.delete(reservation)
    override suspend fun updateReservation(reservation: Reservation) = appDao.update(reservation)
}