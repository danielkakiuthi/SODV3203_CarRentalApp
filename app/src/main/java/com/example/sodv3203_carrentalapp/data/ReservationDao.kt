package com.example.sodv3203_carrentalapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ReservationDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(reservation: Reservation)

    @Update
    suspend fun update(reservation: Reservation)

    @Delete
    suspend fun delete(reservation: Reservation)

    @Query("SELECT * FROM reservations WHERE id = :id")
    fun getReservation(id: Int): Flow<Reservation>

    @Query("SELECT * FROM reservations ORDER BY id ASC")
    fun getAllReservations(): Flow<List<Reservation>>
}