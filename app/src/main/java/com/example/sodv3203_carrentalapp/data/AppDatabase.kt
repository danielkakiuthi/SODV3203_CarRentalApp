package com.example.sodv3203_carrentalapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.text.Format
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Database(entities = [User::class, Car::class, Reservation::class], version = 3, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun appDao(): AppDao

    companion object {
        @Volatile
        private var Instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, AppDatabase::class.java, "app_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}


class DateConverter {
    @TypeConverter
    fun dateToString(dateInput: Date): String {
        val formatter: Format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
        return formatter.format(dateInput)
    }

    @TypeConverter
    fun stringToDate(stringInput: String): Date? {
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
        return formatter.parse(stringInput)
    }
}