package com.example.sodv3203_carrentalapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.twotone.ExitToApp
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerFormatter
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.PlainTooltipBox
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sodv3203_carrentalapp.R
import com.example.sodv3203_carrentalapp.data.AppUiState
import com.example.sodv3203_carrentalapp.data.Car
import com.example.sodv3203_carrentalapp.ui.theme.SODV3203_CarRentalAppTheme

@Composable
fun DisplayPageBooking(
    appUiState: AppUiState,
    modifier: Modifier = Modifier,
    onBackButtonClicked: () -> Unit = {},
    onSignOutButtonClicked: () -> Unit = {},
    onLandingButtonClicked: () -> Unit = {},
    onProfileButtonClicked: () -> Unit = {},
    onBookingButtonClicked: () -> Unit = {},
    onHistoryButtonClicked: () -> Unit = {},
    onSearchButtonClicked: (Car) -> Unit = {}

) {
    val carsList: List<Car> = appUiState.listAllRegisteredCars
    var text by remember { mutableStateOf("") }
    val selectedCar: Car = appUiState.selectedCar ?: carsList[0]
    val datePickerState = rememberDatePickerState(initialDisplayMode = DisplayMode.Input)


    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.errorContainer)
//            .fillMaxWidth()
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .padding(start = 14.dp, end = 14.dp, top = 14.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.user),
                contentDescription = "User"
            )
            Text(
                text = appUiState.loggedUser?.username ?: "username",
                modifier = Modifier.padding(horizontal = 10.dp)
            )
            Spacer(
                modifier = Modifier
                    .weight(1f)
            )
            Image(
                painter = painterResource(id = R.drawable.bell),
                contentDescription = "Notifications"
            )
            Button(
                onClick = onSignOutButtonClicked,
                contentPadding = PaddingValues(1.dp)
            ) {
                Image(
                    imageVector = Icons.TwoTone.ExitToApp,
                    contentDescription = "Logout",
                    modifier = Modifier.height(50.dp)
                )
                Text(text = "Logout", fontSize = 13.sp)
            }
        }

        Row(
            modifier = Modifier
                .weight(1f)
                .padding(top = 16.dp)
        ) {
            Column {
                Text(
                    text = "Booking Information",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Image(

                    painter = painterResource(
                        id = selectedCar.imageResourceId ?: R.drawable.seach_demo
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .height(100.dp)
                        .fillMaxWidth()
                        .padding(bottom = 0.dp)
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
                Row {
                    Text(
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        text = "Car features",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 18.dp, bottom = 18.dp)
                    )
                }
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Card(
                        modifier = Modifier
                            .width(160.dp)
                            .height(50.dp)
                            .clip(RoundedCornerShape(24.dp))
                    ) {
                        Column(
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp)
                        ) {
                            Text(
                                text = "Audio system",
                                fontWeight = FontWeight.Bold,
                            )
                            Text(
                                text = stringResource( selectedCar.category ),
                                color = MaterialTheme.colorScheme.surfaceTint
                            )
                        }
                    }
                    Card(
                        modifier = Modifier
                            .width(160.dp)
                            .height(50.dp)
                            .clip(RoundedCornerShape(24.dp))
                    ) {
                        Column(
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxWidth()

                                .padding(start = 16.dp)
                        ) {
                            Text(
                                text = "Connectivity",
                                fontWeight = FontWeight.Bold,
                            )
                            Text(
                                text = stringResource( selectedCar.feature),
                                color = MaterialTheme.colorScheme.surfaceTint
                            )
                        }
                    }
                }
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp)
                ) {
                    Card(
                        modifier = Modifier
                            .width(160.dp)
                            .height(50.dp)
                            .clip(RoundedCornerShape(24.dp))
                            .padding(vertical = 5.dp)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp)
                        ) {
                            Text(
                                text = "Audio system",
                                fontWeight = FontWeight.Bold,
                            )
                            Text(
                                text = stringResource( selectedCar.category ),
                                color = MaterialTheme.colorScheme.surfaceTint
                            )
                        }
                    }
                    Card(
                        modifier = Modifier
                            .width(160.dp)
                            .height(50.dp)
                            .clip(RoundedCornerShape(24.dp))
                            .padding(vertical = 5.dp)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxWidth()

                                .padding(start = 16.dp)
                        ) {
                            Text(
                                text = "Connectivity",
                                fontWeight = FontWeight.Bold,
                            )
                            Text(
                                text = stringResource( selectedCar.feature),
                                color = MaterialTheme.colorScheme.surfaceTint
                            )
                        }
                    }
                }
                Text(
                    text = "Order Summary",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                Column(
                    modifier = Modifier
                        .padding(15.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Ford Focus or similar",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "$51/day, 2 days",
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
                        location="Calgary",
                        datetime="Fri, Jan 6, 12:00PM"
                    )
                    Card_PickupDropoff(
                        location="Edmonton",
                        datetime="Sun, Jan 8, 12:00PM"
                    )
                }
            }

        }
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .height(80.dp)
                .fillMaxWidth()
                .padding(vertical = 20.dp)
        ){
            PlainTooltipBox(
                tooltip = { Text("Add to favorites" ) },
                contentColor = Color.White
            ) {
                Image(
                    painter = painterResource(id = R.drawable.home),
                    contentDescription = null,
                    modifier = Modifier
                        .clickable { onLandingButtonClicked() }
                        .height(25.dp)
                        .tooltipAnchor()
                )
            }
            Image(
                painter = painterResource(id = R.drawable.booking),
                contentDescription = null,
                modifier = Modifier
                    .clickable { onBookingButtonClicked() }
                    .height(25.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = null,
                modifier = Modifier
                    .clickable { onProfileButtonClicked() }
                    .height(25.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.history),
                contentDescription = null,
                modifier = Modifier
                    .clickable { onHistoryButtonClicked() }
                    .height(25.dp)
            )
        }
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
                modifier = Modifier.fillMaxHeight().width(30.dp)
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

@Preview(showBackground = true, heightDp = 800)
@Composable
fun DisplayPageBookingPreview() {
    SODV3203_CarRentalAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            DisplayPageBooking(
                appUiState = AppUiState()
            )
        }
    }
}