package com.example.sodv3203_carrentalapp.data

import kotlinx.coroutines.flow.Flow

class CarRepositoryOffline(private val carDao: CarDao) : CarRepository {

    override fun getAllCarsStream(): Flow<List<Car>> = carDao.getAllCars()
    override fun getCarStream(id: Int): Flow<Car?> = carDao.getCar(id)
    override suspend fun insertCar(car: Car) = carDao.insert(car)
    override suspend fun deleteCar(car: Car) = carDao.delete(car)
    override suspend fun updateCar(car: Car) = carDao.update(car)

}