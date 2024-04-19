package com.example.sodv3203_carrentalapp.data

import android.content.Context
import android.icu.text.SimpleDateFormat
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.sodv3203_carrentalapp.R
import java.text.Format
import java.util.Date
import java.util.Locale
import java.util.concurrent.Executors

@Database(entities = [User::class, Car::class, Reservation::class], version = 10, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun appDao(): AppDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                AppDatabase::class.java, "app_database")
                // prepopulate the database after onCreate was called
                .fallbackToDestructiveMigration()
                .createFromAsset("app_database.db")
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        // insert the data on the IO Thread
                        ioThread {
                            getInstance(context).appDao().insertListCars(PREPOPULATE_DATA_LIST_CARS)
                            getInstance(context).appDao().insertListUsers(PREPOPULATE_DATA_LIST_USERS)
                            getInstance(context).appDao().insertListReservations(PREPOPULATE_DATA_LIST_RESERVATIONS)
                        }
                    }
                })
                .build()

        val sdf: SimpleDateFormat = SimpleDateFormat("dd/M/yyyy hh:mm:ss", Locale.ENGLISH)
        val PREPOPULATE_DATA_LIST_USERS = listOf(
            User(0, "admin", "admin123", "John","Doe","11/19/2020", "123456789", "johndoe@mybvc.ca")
        )
        val PREPOPULATE_DATA_LIST_CARS = listOf(
            Car(0, R.drawable.car01, "Ford Focus", "Eco Boost", "Sedan",4,3,4 ),
            Car(0, R.drawable.car02, "Toyota Camry", "Comfort Mode", "Sedan",4,4,4 ),
            Car(0, R.drawable.car03, "Nissan Leaf", "Flex Fuel", "Sedan",4,2,4 ),
            Car(0, R.drawable.car04, "Honda CRV", "Vtec LEV", "SUVs",5,5,4 ),
            Car(0, R.drawable.car05, "KIA Sportage", "Full time 4 wheels", "SUVs",5,5,4 ),
            Car(0, R.drawable.car06, "Toyota RAV4", "Hybrid vehicle", "SUVs",5,5,4 ),
            Car(0, R.drawable.car07, "Volkswagen Transporter", "Multipurpose seat", "Vans",10,6,3 )
        )
        val PREPOPULATE_DATA_LIST_RESERVATIONS = listOf(
            Reservation(
                id = 0,
                userId = 0,
                carId = 0,
                location = "Calgary",
                startDate = sdf.parse("06/2/2024 12:00:00"),
                endDate = sdf.parse("08/2/2024 12:00:00"),
                pricePerDay = 51.0f,
                additionalRequests = "",
                nameOnCard = "John Doe",
                cardNumber = "1111222233334444",
                cvc = "123"
            ),
            Reservation(
                id = 0,
                userId = 0,
                carId = 0,
                location = "Calgary",
                startDate = sdf.parse("07/2/2024 12:00:00"),
                endDate = sdf.parse("14/2/2024 12:00:00"),
                pricePerDay = 53.0f,
                additionalRequests = "",
                nameOnCard = "John Doe",
                cardNumber = "1111222233334444",
                cvc = "123"
            ),
            Reservation(
                id = 0,
                userId = 1,
                carId = 0,
                location = "Calgary",
                startDate = sdf.parse("06/2/2024 12:00:00"),
                endDate = sdf.parse("08/2/2024 12:00:00"),
                pricePerDay = 30.0f,
                additionalRequests = "",
                nameOnCard = "Ximena Compton",
                cardNumber = "1111222233334444",
                cvc = "123"
            ),
            Reservation(
                id = 0,
                userId = 1,
                carId = 1,
                location = "Calgary",
                startDate = sdf.parse("10/2/2024 12:00:00"),
                endDate = sdf.parse("14/2/2024 12:00:00"),
                pricePerDay = 40.0f,
                additionalRequests = "",
                nameOnCard = "Ximena Compton",
                cardNumber = "1111222233334444",
                cvc = "123"
            ),
            Reservation(
                id = 0,
                userId = 1,
                carId = 6,
                location = "Calgary",
                startDate = sdf.parse("20/2/2024 12:00:00"),
                endDate = sdf.parse("21/2/2024 12:00:00"),
                pricePerDay = 38.0f,
                additionalRequests = "",
                nameOnCard = "Ximena Compton",
                cardNumber = "1111222233334444",
                cvc = "123"
            ),
            Reservation(
                id = 0,
                userId = 1,
                carId = 5,
                location = "Calgary",
                startDate = sdf.parse("22/2/2024 12:00:00"),
                endDate = sdf.parse("28/2/2024 12:00:00"),
                pricePerDay = 42.5f,
                additionalRequests = "",
                nameOnCard = "Ximena Compton",
                cardNumber = "1111222233334444",
                cvc = "123"
            ),
            Reservation(
                id = 0,
                userId = 0,
                carId = 1,
                location = "Calgary",
                startDate = sdf.parse("01/1/2024 12:00:00"),
                endDate = sdf.parse("4/1/2024 12:00:00"),
                pricePerDay = 51.90f,
                additionalRequests = "",
                nameOnCard = "John Doe",
                cardNumber = "1111222233334444",
                cvc = "123"
            ),
            Reservation(
                id = 0,
                userId = 1,
                carId = 2,
                location = "Calgary",
                startDate = sdf.parse("04/2/2024 12:00:00"),
                endDate = sdf.parse("04/2/2024 12:00:00"),
                pricePerDay = 20.0f,
                additionalRequests = "",
                nameOnCard = "Ximena Compton",
                cardNumber = "1111222233334444",
                cvc = "123"
            ),
            Reservation(
                id = 0,
                userId = 1,
                carId = 3,
                location = "Calgary",
                startDate = sdf.parse("12/2/2024 12:00:00"),
                endDate = sdf.parse("14/2/2024 12:00:00"),
                pricePerDay = 39.0f,
                additionalRequests = "",
                nameOnCard = "Ximena Compton",
                cardNumber = "1111222233334444",
                cvc = "123"
            ),
            Reservation(
                id = 0,
                userId = 1,
                carId = 4,
                location = "Calgary",
                startDate = sdf.parse("22/2/2024 12:00:00"),
                endDate = sdf.parse("23/2/2024 12:00:00"),
                pricePerDay = 25.0f,
                additionalRequests = "",
                nameOnCard = "Ximena Compton",
                cardNumber = "1111222233334444",
                cvc = "123"
            ),
            Reservation(
                id = 0,
                userId = 1,
                carId = 5,
                location = "Calgary",
                startDate = sdf.parse("25/2/2024 12:00:00"),
                endDate = sdf.parse("1/3/2024 12:00:00"),
                pricePerDay = 32.5f,
                additionalRequests = "",
                nameOnCard = "Ximena Compton",
                cardNumber = "1111222233334444",
                cvc = "123"
            ),
        )
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



private val IO_EXECUTOR = Executors.newSingleThreadExecutor()

fun ioThread(f : () -> Unit) {
    IO_EXECUTOR.execute(f)
}