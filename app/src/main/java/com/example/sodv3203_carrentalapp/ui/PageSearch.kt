package com.example.sodv3203_carrentalapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sodv3203_carrentalapp.R
import com.example.sodv3203_carrentalapp.data.AppUiState
import com.example.sodv3203_carrentalapp.data.Car
import com.example.sodv3203_carrentalapp.ui.theme.SODV3203_CarRentalAppTheme

@Composable
fun DisplayPageSearch(
    appUiState: AppUiState,
    modifier: Modifier = Modifier,
    onBackButtonClicked: () -> Unit = {},
    onSelectButtonClicked: () -> Unit = {},
) {

    val carsList: List<Car> = appUiState.listAllRegisteredCars
    val selectedCar: Car = appUiState.selectedCar ?: carsList[0]

    Column(
        modifier = modifier,
    ) {
        Row {
            Image(
                painter = painterResource(id = R.drawable.back),
                contentDescription = null,
                modifier = Modifier
                    .clickable { onBackButtonClicked() }
                    .size(26.dp)
            )
        }
        Image(
//            contentScale = ContentScale.Fit,
            painter = painterResource(id = selectedCar.imageResourceId ?: R.drawable.seach_demo),
            contentDescription =  null,
            modifier = Modifier
                .height(280.dp)
                .fillMaxWidth()
                .padding(bottom = 0.dp)
        )
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
        ) {
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
                        text = selectedCar.bags.toString() ,
                    )
                    Text(
                        text = "bags",
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
        }
        Row {
            Text(
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
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
                       .height(80.dp)
                       .clip(RoundedCornerShape(24.dp))
            ) {
                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
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
                    .height(80.dp)
                    .clip(RoundedCornerShape(24.dp))
            ) {
                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
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
            text = "Rental terms",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(top = 8.dp , bottom = 8.dp)
            )
        Column {
            Row (
//                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp)
//                    .padding(bottom = 4.dp)
            ){
                Text(text = "Pay at pickup")
                Spacer(Modifier.width(210.dp))
                Image(
                    painter = painterResource(id = R.drawable.credit)  ,
                    contentDescription = null,
//                    Modifier.size(40.dp)
                )
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(32.dp)
                    .padding(top = 10.dp)
            ){
                Text(text = "Free cancellation")
                Spacer(Modifier.width(185.dp))
                Image(
                    painter = painterResource(id = R.drawable.cancel),
                    contentDescription = null,
//                    modifier = Modifier
//                        .width(80.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Column (
            modifier = Modifier
        ){
            Row (
            ){
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = onSelectButtonClicked,
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.error ),
                    ) {
                    Text( "Select this car")
                }
            }
        }


//        Row(
//            modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))
//        ) {
//            Column(
//                verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
//            ) {
//                Button(
//                    modifier = Modifier.fillMaxWidth(),
//                    onClick = { onSelectButtonClicked() }
//                ) {
//                    Text(stringResource(R.string.button_next))
//                }
//                OutlinedButton(
//                    modifier = Modifier.fillMaxWidth(),
//                    onClick = { onBackButtonClicked() }
//                ) {
//                    Text(stringResource(R.string.button_back))
//                }
//            }
//        }
    }
}


@Preview(showBackground = true, heightDp = 800)
@Composable
fun DisplayPageSearchPreview() {
    SODV3203_CarRentalAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            DisplayPageSearch(
                appUiState = AppUiState()
            )
        }
    }
}