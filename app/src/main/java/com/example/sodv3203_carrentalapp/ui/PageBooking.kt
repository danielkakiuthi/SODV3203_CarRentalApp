package com.example.sodv3203_carrentalapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sodv3203_carrentalapp.data.Car
import com.example.sodv3203_carrentalapp.data.Reservation
import com.example.sodv3203_carrentalapp.ui.theme.SODV3203_CarRentalAppTheme
import kotlin.math.abs

@Composable
fun DisplayPageBooking(
    appUiState: AppUiState,
    modifier: Modifier = Modifier,
    onLandingButtonClicked: () -> Unit,
    onProfileButtonClicked: () -> Unit,
    onHistoryButtonClicked: () -> Unit,
    onAddNewCarButtonClicked: () -> Unit
) {
    val selectedReservation: Reservation = appUiState.selectedReservation ?: appUiState.placeholderReservation
    val dayDifference = abs(selectedReservation.endDate.time - selectedReservation.startDate.time) / (24*60*60*1000)

    var selectedCar: Car = appUiState.selectedCar ?: appUiState.placeholderCar

    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "Booking Information",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Image(
                painter = painterResource(id = selectedCar.imageResourceId),
                contentDescription = null,
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
                    .padding(bottom = 0.dp)
            )
            Text(
                text = selectedCar.name,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Card(
                    modifier = Modifier
                        .width(80.dp)
                        .height(80.dp)
                        .clip(RoundedCornerShape(24.dp))
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                    ) {
                        Text(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            text = selectedCar.seat.toString(),
                        )
                        Text(
                            text = "seats",
                            color = MaterialTheme.colorScheme.surfaceTint
                        )
                    }
                }
                Card (
                    modifier = Modifier
                        .width(80.dp)
                        .height(80.dp)
                        .clip(RoundedCornerShape(24.dp))
                ){
                    Column (
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                    ){
                        Text(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            text = selectedCar.doors.toString()  ,
                        )
                        Text(
                            text = "doors",
                            color = MaterialTheme.colorScheme.surfaceTint
                        )
                    }
                }
                Card (
                    modifier = Modifier
                        .width(80.dp)
                        .height(80.dp)
                        .clip(RoundedCornerShape(24.dp))
                ){
                    Column (
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                    ){
                        Text(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            text = selectedCar.bags.toString() ,
                        )
                        Text(
                            text = "bags",
                            color = MaterialTheme.colorScheme.surfaceTint
                        )
                    }
                }
            }
            Column(modifier = Modifier.padding(vertical = 15.dp)) {
                Text(
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    text = "Car features",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                )
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Card_Addons(title = "Category", information = selectedCar.category)
                    Card_Addons(title = "Features", information = selectedCar.feature)
                }
            }

            Column(
                modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Order Summary",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp)
                )
                Text(
                    text = selectedCar.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "$${selectedReservation.pricePerDay}/day, $dayDifference days",
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Pickup & Dropoff",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                )
                Card_PickupDropoff(
                    location = selectedReservation.location,
                    datetime = selectedReservation.startDate.toString()
                )
                Card_PickupDropoff(
                    location = selectedReservation.location,
                    datetime = selectedReservation.endDate.toString()
                )
            }
        }

        BottomNavigationBar(
            onLandingButtonClicked = onLandingButtonClicked,
            onProfileButtonClicked = onProfileButtonClicked,
            onHistoryButtonClicked = onHistoryButtonClicked,
            onAddNewCarButtonClicked = onAddNewCarButtonClicked
        )

    }
}

@Composable
private fun Card_PickupDropoff(
    location: String,
    datetime: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .clip(RoundedCornerShape(24.dp))
            .padding(5.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {
            Icon(
                imageVector =  Icons.Rounded.LocationOn,
                contentDescription = "Location",
                modifier = Modifier
                    .fillMaxHeight()
                    .width(30.dp)
            )
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(start = 10.dp)
            ) {
                Text(
                    text = location,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = datetime,
                    color = MaterialTheme.colorScheme.surfaceTint
                )
            }
        }
    }
}




@Preview(showBackground = true, heightDp = 1000)
@Composable
fun DisplayPageBookingPreview() {
    SODV3203_CarRentalAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            DisplayPageBooking(
                appUiState = AppUiState(),
                onLandingButtonClicked = {},
                onProfileButtonClicked = {},
                onHistoryButtonClicked = {},
                onAddNewCarButtonClicked = {}
            )
        }
    }
}