package com.example.sodv3203_carrentalapp.ui

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sodv3203_carrentalapp.R
import com.example.sodv3203_carrentalapp.data.AppUiState
import com.example.sodv3203_carrentalapp.data.Reservation
import com.example.sodv3203_carrentalapp.ui.theme.SODV3203_CarRentalAppTheme
import kotlin.math.abs

@Composable
fun DisplayPageSummary(
    appUiState: AppUiState,
    modifier: Modifier = Modifier,
    onBackButtonClicked: () -> Unit = {},
    onConfirmButtonClicked: () -> Unit = {}
) {

    val context = LocalContext.current.applicationContext
    val selectedReservation: Reservation = appUiState.selectedReservation ?: appUiState.placeholderReservation
    val dayDifference = abs(selectedReservation.endDate.time - selectedReservation.startDate.time) / (24*60*60*1000)


    Column(
        modifier = modifier
            .padding(10.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Order Summary",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Image(
            painter = painterResource(id = selectedReservation.car.imageResourceId),
            contentDescription = null,
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
                .padding(bottom = 0.dp)
        )
        Column(modifier = Modifier.padding(10.dp)) {
            Text(
                text = selectedReservation.car.name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "$${selectedReservation.pricePerDay}/day, ${dayDifference} days"
            )
        }

        Column(modifier = Modifier.padding(10.dp)) {
            Text(text = "Add-Ons", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Card_Addons(title = "Category", information = selectedReservation.car.category)
                Card_Addons(title = "Features", information = selectedReservation.car.feature)
            }

        }

        Column(modifier = Modifier.padding(10.dp)) {
            Text(text = "Pickup & Drop-off", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Card_PickupDropoff(
                location = selectedReservation.location,
                datetime = selectedReservation.startDate.toString()
            )
            Icon(
                imageVector =  Icons.Filled.MoreVert,
                contentDescription = "Location"
            )
            Icon(
                imageVector =  Icons.Filled.KeyboardArrowDown,
                contentDescription = "Location"
            )
            Card_PickupDropoff(
                location = selectedReservation.location,
                datetime = selectedReservation.endDate.toString()
            )
        }

        Row(
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        onConfirmButtonClicked()
                        Toast.makeText(context, "Reservation Successful", Toast.LENGTH_SHORT).show()
                    }
                ) {
                    Text(stringResource(R.string.button_send))
                }
//                OutlinedButton(
//                    modifier = Modifier.fillMaxWidth(),
//                    onClick = { onBackButtonClicked() }
//                ) {
//                    Text(stringResource(R.string.button_back))
//                }
            }
        }
    }
}


@Composable
fun Card_Addons(
    title: String,
    information: String
) {
    Card(
        modifier = Modifier
            .width(160.dp)
            .height(80.dp)
            .clip(RoundedCornerShape(40.dp))
            .padding(vertical = 5.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = information,
                color = MaterialTheme.colorScheme.surfaceTint
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
            .height(80.dp)
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
                    .padding(start = 16.dp)
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


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true, heightDp = 800)
@Composable
fun DisplayPageSummaryPreview() {
    SODV3203_CarRentalAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            DisplayPageSummary(
                appUiState = AppUiState()
            )
        }
    }
}