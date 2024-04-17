package com.example.sodv3203_carrentalapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface CarDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(car: Car)

    @Update
    suspend fun update(car: Car)

    @Delete
    suspend fun delete(car: Car)

    @Query("SELECT * FROM cars WHERE id = :id")
    fun getCar(id: Int): Flow<Car>

    @Query("SELECT * FROM cars ORDER BY id ASC")
    fun getAllCars(): Flow<List<Car>>
}