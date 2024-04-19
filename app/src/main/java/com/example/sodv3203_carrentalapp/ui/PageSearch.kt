package com.example.sodv3203_carrentalapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sodv3203_carrentalapp.R
import com.example.sodv3203_carrentalapp.data.Car
import com.example.sodv3203_carrentalapp.ui.theme.SODV3203_CarRentalAppTheme

@Composable
fun DisplayPageSearch(
    appUiState: AppUiState,
    onBackButtonClicked: () -> Unit,
    onSelectButtonClicked: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AppViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {

    val selectedCar: Car = appUiState.selectedCar ?: appUiState.placeholderCar

    Column(
        modifier = modifier
            .padding(20.dp)

    ) {
        Column(modifier=Modifier.verticalScroll(rememberScrollState())) {

        }
        Image(
            painter = painterResource(id = selectedCar.imageResourceId),
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

        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp)
        ) {
            Card_Addons(title = "Category", information = selectedCar.category)
            Card_Addons(title = "Features", information = selectedCar.feature)
        }


        Column {
            Text(
                text = "Rental terms",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = 8.dp , bottom = 8.dp)
            )
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp)
            ){
                Text(text = "Pay at pickup")
                Spacer(Modifier.width(210.dp))
                Image(
                    painter = painterResource(id = R.drawable.credit)  ,
                    contentDescription = null,
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
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Column (
            modifier = Modifier
        ){
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = onSelectButtonClicked,
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.error ),
                ) {
                Text( "Select this car")
            }
            OutlinedButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = { onBackButtonClicked() }
            ) {
                Text(stringResource(R.string.button_back))
            }
        }
    }
}


@Preview(showBackground = true, heightDp = 800)
@Composable
fun DisplayPageSearchPreview() {
    SODV3203_CarRentalAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            DisplayPageSearch(
                appUiState = AppUiState(),
                onBackButtonClicked = {},
                onSelectButtonClicked = {}
            )
        }
    }
}