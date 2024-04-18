package com.example.sodv3203_carrentalapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sodv3203_carrentalapp.R
import com.example.sodv3203_carrentalapp.data.Car
import com.example.sodv3203_carrentalapp.data.Reservation
import com.example.sodv3203_carrentalapp.ui.theme.SODV3203_CarRentalAppTheme
import kotlin.math.abs


@Composable
fun DisplayPageHistory(
    appUiState: AppUiState,
    modifier: Modifier = Modifier,
    onCardBookingClick: (reservation: Reservation) -> Unit = {},
    onBackButtonClicked: () -> Unit = {}
) {
    var listReservationsCurrentUser = mutableListOf<Reservation>()
    val currentUserId = (appUiState.loggedUser ?: appUiState.placeholderUser).id

    appUiState.listAllReservations.forEach {
        if(currentUserId==it.userId) {
            listReservationsCurrentUser.add(it)
        }
    }


    Column(
        modifier = modifier
    ) {
        Text(
            text = "Past Booking Information",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )

        if(appUiState.listAllUsers.isNotEmpty()) {
            LazyColumn {
                items(listReservationsCurrentUser) { reservation ->
                    var selectedCar: Car = appUiState.placeholderCar
                    for (car in appUiState.listAllRegisteredCars) {
                        if(car.id == reservation.carId) {
                            selectedCar = car
                            break
                        }
                    }
                    CardBooking(
                        reservation = reservation,
                        selectedCar = selectedCar,
                        onCardBookingClick = onCardBookingClick
                    )
                }
            }
        }


        Row(
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
            ) {
                OutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { onBackButtonClicked() }
                ) {
                    Text(stringResource(R.string.button_back))
                }
            }
        }
    }
}


@Composable
private fun CardBooking(
    reservation: Reservation,
    selectedCar: Car,
    onCardBookingClick: (reservation: Reservation) -> Unit
) {
    val dayDifference = abs(reservation.endDate.time - reservation.startDate.time) / (24*60*60*1000)

    Card(
        onClick = { onCardBookingClick(reservation) },
        modifier = Modifier.padding(10.dp)
    ) {
        Row {
            Image(
                painter = painterResource(id = selectedCar.imageResourceId),
                contentDescription = null,
                modifier = Modifier
                    .height(100.dp)
                    .padding(bottom = 0.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .weight(0.20f)
            )
            Column(
                modifier = Modifier
                    .weight(0.80f)
                    .padding(10.dp)
            ) {

                Text(text = selectedCar.name, fontWeight = FontWeight.Bold)
                Text(text = "From: ${reservation.startDate}", fontSize = 12.sp)
                Text(text = "To: ${reservation.endDate}", fontSize = 12.sp)
                Text(text = "Price: $${reservation.pricePerDay}/day, $dayDifference days", fontSize = 12.sp)
                val totalPrice:Double = String.format("%.2f", reservation.pricePerDay * dayDifference).toDouble()
                Text(text = "Total Price: $${totalPrice}", fontSize = 12.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}


@Preview(showBackground = true, heightDp = 800)
@Composable
fun DisplayPageHistoryPreview() {
    SODV3203_CarRentalAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            DisplayPageHistory(
                appUiState = AppUiState()
            )
        }
    }
}