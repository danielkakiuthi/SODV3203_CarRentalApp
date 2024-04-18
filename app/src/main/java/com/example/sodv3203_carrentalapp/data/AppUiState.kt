package com.example.sodv3203_carrentalapp.data

import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.example.sodv3203_carrentalapp.R
import java.util.Locale

data class AppUiState @RequiresApi(Build.VERSION_CODES.O) constructor(
    /*----------------------------------------------------------------------------------------------
    * ---------------------------------------- STATE -----------------------------------------------
    * ---------------------------------------------------------------------------------------------- */

    val sdf: SimpleDateFormat = SimpleDateFormat("dd/M/yyyy hh:mm:ss", Locale.ENGLISH),

    //User State
    val isUserLoggedIn: Boolean = false,
    val loggedUser: User? = null,

    //Car State
    val selectedCar: Car? = null,

    //Reservation State
    val selectedReservation: Reservation? = null,


    //Placeholders for errors
    val placeholderUser: User = User(0, "[PLACEHOLDER_USERNAME]", "[PLACEHOLDER_PASSWORD]", "[PLACEHOLDER_FIRSTNAME]","[PLACEHOLDER_LASTNAME]","[PLACEHOLDER_BIRTHDATE]", "[PLACEHOLDER_PHONE]", "[PLACEHOLDER_EMAIL]"),
    val placeholderCar: Car = Car(0, R.drawable.car00, "[PLACEHOLDER_NAME]", "[PLACEHOLDER_FEATURE]", "[PLACEHOLDER_CATEGORY]",0,0,0 ),
    val placeholderReservation: Reservation = Reservation(
        id = 0,
        user = placeholderUser,
        car = placeholderCar,
        location = "[PLACEHOLDER_LOCATION]",
        startDate = sdf.parse("06/2/2024 12:00:00"),
        endDate = sdf.parse("08/2/2024 12:00:00"),
        pricePerDay = 1000.0f,
        nameOnCard = "[PLACEHOLDER_NAME_ON_CARD]",
        additionalRequests = "[PLACEHOLDER_ADDITIONAL_REQUESTS]",
        cardNumber = "[PLACEHOLDER_CAR_Number]",
        cvc = "[PLACEHOLDER_CVC]"
    ),

    /*----------------------------------------------------------------------------------------------
    * -------------------------------------- DATABASE ----------------------------------------------
    * ---------------------------------------------------------------------------------------------- */
    //Database Users
    val listAllUsers: List<User> = mutableListOf(
        User(1, "admin", "admin123", "John","Doe","11/19/2020", "123456789", "johndoe@mybvc.ca")
    ),

    //Database Registered Cars
    val listAllRegisteredCars: List<Car> = mutableListOf(
        Car(1, R.drawable.car01, "Ford Focus", "Eco Boost", "Sedan",4,3,4 ),
        Car(2, R.drawable.car02, "Toyota Camry", "Comfort Mode", "Sedan",4,4,4 ),
        Car(3, R.drawable.car03, "Nissan Leaf", "Flex Fuel", "Sedan",4,2,4 ),
        Car(4, R.drawable.car04, "Honda CRV", "Vtec LEV", "SUVs",5,5,4 ),
        Car(5, R.drawable.car05, "KIA Sportage", "Full time 4 wheels", "SUVs",5,5,4 ),
        Car(6, R.drawable.car06, "Toyota RAV4", "Hybrid vehicle", "SUVs",5,5,4 ),
        Car(7, R.drawable.car07, "Volkswagen Transporter", "Multipurpose seat", "Vans",10,6,3 )
    ),

    //Database Reservations
    val listAllReservations: List<Reservation> = mutableListOf(
        Reservation(
            id = 0,
            user = placeholderUser,
            car = placeholderCar,
            location = "Calgary",
            startDate = sdf.parse("06/2/2024 12:00:00"),
            endDate = sdf.parse("08/2/2024 12:00:00"),
            pricePerDay = 51.0f,
            additionalRequests = "",
            nameOnCard = "${placeholderUser.firstName} ${placeholderUser.lastName}",
            cardNumber = "1111222233334444",
            cvc = "123"
        ),
        Reservation(
            id = 1,
            user = placeholderUser,
            car = placeholderCar,
            location = "Calgary",
            startDate = sdf.parse("07/2/2024 12:00:00"),
            endDate = sdf.parse("14/2/2024 12:00:00"),
            pricePerDay = 53.0f,
            additionalRequests = "",
            nameOnCard = "${placeholderUser.firstName} ${placeholderUser.lastName}",
            cardNumber = "1111222233334444",
            cvc = "123"
        ),
        Reservation(
            id = 2,
            user = listAllUsers[0],
            car = listAllRegisteredCars[0],
            location = "Calgary",
            startDate = sdf.parse("06/2/2024 12:00:00"),
            endDate = sdf.parse("08/2/2024 12:00:00"),
            pricePerDay = 30.0f,
            additionalRequests = "",
            nameOnCard = "${listAllUsers[0].firstName} ${listAllUsers[0].lastName}",
            cardNumber = "1111222233334444",
            cvc = "123"
        ),
        Reservation(
            id = 3,
            user = listAllUsers[0],
            car = listAllRegisteredCars[1],
            location = "Calgary",
            startDate = sdf.parse("10/2/2024 12:00:00"),
            endDate = sdf.parse("14/2/2024 12:00:00"),
            pricePerDay = 40.0f,
            additionalRequests = "",
            nameOnCard = "${listAllUsers[0].firstName} ${listAllUsers[0].lastName}",
            cardNumber = "1111222233334444",
            cvc = "123"
        ),
        Reservation(
            id = 4,
            user = listAllUsers[0],
            car = listAllRegisteredCars[6],
            location = "Calgary",
            startDate = sdf.parse("20/2/2024 12:00:00"),
            endDate = sdf.parse("21/2/2024 12:00:00"),
            pricePerDay = 38.0f,
            additionalRequests = "",
            nameOnCard = "${listAllUsers[0].firstName} ${listAllUsers[0].lastName}",
            cardNumber = "1111222233334444",
            cvc = "123"
        ),
        Reservation(
            id = 5,
            user = listAllUsers[0],
            car = listAllRegisteredCars[5],
            location = "Calgary",
            startDate = sdf.parse("22/2/2024 12:00:00"),
            endDate = sdf.parse("28/2/2024 12:00:00"),
            pricePerDay = 42.5f,
            additionalRequests = "",
            nameOnCard = "${listAllUsers[0].firstName} ${listAllUsers[0].lastName}",
            cardNumber = "1111222233334444",
            cvc = "123"
        ),
        Reservation(
            id = 6,
            user = listAllUsers[0],
            car = listAllRegisteredCars[0],
            location = "Calgary",
            startDate = sdf.parse("01/1/2024 12:00:00"),
            endDate = sdf.parse("4/1/2024 12:00:00"),
            pricePerDay = 51.90f,
            additionalRequests = "",
            nameOnCard = "${placeholderUser.firstName} ${placeholderUser.lastName}",
            cardNumber = "1111222233334444",
            cvc = "123"
        ),
        Reservation(
            id = 7,
            user = listAllUsers[0],
            car = listAllRegisteredCars[1],
            location = "Calgary",
            startDate = sdf.parse("04/2/2024 12:00:00"),
            endDate = sdf.parse("04/2/2024 12:00:00"),
            pricePerDay = 20.0f,
            additionalRequests = "",
            nameOnCard = "${listAllUsers[0].firstName} ${listAllUsers[0].lastName}",
            cardNumber = "1111222233334444",
            cvc = "123"
        ),
        Reservation(
            id = 8,
            user = listAllUsers[0],
            car = listAllRegisteredCars[2],
            location = "Calgary",
            startDate = sdf.parse("12/2/2024 12:00:00"),
            endDate = sdf.parse("14/2/2024 12:00:00"),
            pricePerDay = 39.0f,
            additionalRequests = "",
            nameOnCard = "${listAllUsers[0].firstName} ${listAllUsers[0].lastName}",
            cardNumber = "1111222233334444",
            cvc = "123"
        ),
        Reservation(
            id = 9,
            user = listAllUsers[0],
            car = listAllRegisteredCars[3],
            location = "Calgary",
            startDate = sdf.parse("22/2/2024 12:00:00"),
            endDate = sdf.parse("23/2/2024 12:00:00"),
            pricePerDay = 25.0f,
            additionalRequests = "",
            nameOnCard = "${listAllUsers[0].firstName} ${listAllUsers[0].lastName}",
            cardNumber = "1111222233334444",
            cvc = "123"
        ),
        Reservation(
            id = 10,
            user = listAllUsers[0],
            car = listAllRegisteredCars[4],
            location = "Calgary",
            startDate = sdf.parse("25/2/2024 12:00:00"),
            endDate = sdf.parse("1/3/2024 12:00:00"),
            pricePerDay = 32.5f,
            additionalRequests = "",
            nameOnCard = "${listAllUsers[0].firstName} ${listAllUsers[0].lastName}",
            cardNumber = "1111222233334444",
            cvc = "123"
        ),
    ),
    val welcomeImageSlid: List<WelcomeImage> = listOf(
        WelcomeImage( R.drawable.welcome01),
        WelcomeImage( R.drawable.welcome02),
        WelcomeImage( R.drawable.welcome03),
        WelcomeImage( R.drawable.welcome04),
        WelcomeImage( R.drawable.welcome05)
    )


)