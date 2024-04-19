package com.example.sodv3203_carrentalapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface AppDao {

    /* ----------------------------------------------------------------------
     * ----------------------------- Car Dao --------------------------------
     * ---------------------------------------------------------------------- */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(car: Car)
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertListCars(listCars: List<Car>)
    @Update
    suspend fun update(car: Car)
    @Delete
    suspend fun delete(car: Car)
    @Query("SELECT * FROM cars WHERE id = :id")
    fun getCar(id: Int): Flow<Car>
    @Query("SELECT * FROM cars ORDER BY id ASC")
    fun getAllCars(): Flow<List<Car>>


    /* ----------------------------------------------------------------------
     * ---------------------------- User Dao --------------------------------
     * ---------------------------------------------------------------------- */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: User)
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertListUsers(listUsers: List<User>)
    @Update
    suspend fun update(user: User)
    @Delete
    suspend fun delete(user: User)
    @Query("SELECT * FROM users WHERE id = :id")
    fun getUser(id: Int): Flow<User>
    @Query("SELECT * FROM users ORDER BY id ASC")
    fun getAllUsers(): Flow<List<User>>


    /* ----------------------------------------------------------------------
     * ------------------------- Reservation Dao ----------------------------
     * ---------------------------------------------------------------------- */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(reservation: Reservation)
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertListReservations(listReservations: List<Reservation>)
    @Update
    suspend fun update(reservation: Reservation)
    @Delete
    suspend fun delete(reservation: Reservation)
    @Query("SELECT * FROM reservations WHERE id = :id")
    fun getReservation(id: Int): Flow<Reservation>
    @Query("SELECT * FROM reservations ORDER BY id ASC")
    fun getAllReservations(): Flow<List<Reservation>>

}