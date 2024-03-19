package com.example.sodv3203_carrentalapp.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sodv3203_carrentalapp.R
import com.example.sodv3203_carrentalapp.data.AppUiState
import com.example.sodv3203_carrentalapp.data.Car
import com.example.sodv3203_carrentalapp.data.cars
import com.example.sodv3203_carrentalapp.ui.theme.SODV3203_CarRentalAppTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun DisplayPageSearch(
    appUiState: AppUiState,
    modifier: Modifier = Modifier,
    onBackButtonClicked: () -> Unit = {},
    onSelectButtonClicked: () -> Unit = {},


) {



    Column(
        modifier = modifier
    ) {
//        Text(text = "Placeholder: Search Page")
        Image(
//            contentScale = ContentScale.Fit,
            painter = painterResource(id = R.drawable.seach_demo),
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
                    .border(width = 1.dp, color = MaterialTheme.colorScheme.outline)
                    .width(80.dp)
                    .height(80.dp)
            ){
                Column (

                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,


                ){
                    Text(
                        text = cars[0].seat.toString(),
                        )
                    Text(text = "seats")
                }
            }
            Card (

                modifier = Modifier
                    .border(width = 1.dp, color = MaterialTheme.colorScheme.outline)
                    .width(80.dp)
                    .height(80.dp)
            ){
                Column {
                    Text(
                        text = cars[0].bags.toString() ,
                    )
                    Text(text = "bags")
                }
            }
            Card (

                modifier = Modifier
                    .border(width = 1.dp, color = MaterialTheme.colorScheme.outline)
                    .width(80.dp)
                    .height(80.dp)
            ){
                Column {
                    Text(
                        text = cars[0].doors.toString()  ,
                    )
                    Text(text = "doors")
                }
            }
        }
        
        Text(text = "Car features")

        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Card(

                modifier = Modifier
                    .border(width = 1.dp, color = MaterialTheme.colorScheme.outline)
                    .width(160.dp)
                    .height(80.dp)
            ) {
                Column(

                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,


                    ) {
                    Text(text = "Audio system")
                    Text(
                        text = stringResource( cars[0].category )
                    )
                }
            }
            Card(

                modifier = Modifier
                    .border(width = 1.dp, color = MaterialTheme.colorScheme.outline)
                    .width(160.dp)
                    .height(80.dp)
            ) {
                Column {
                    Text(text = "Connectivity")
                    Text(
                        text = stringResource( cars[0].feature )
                    )
                }
            }
        }

        Text(text = "Rental terms")

        Text(text = "Pay at pickup")
        Text(text = "Free cancellation")
        Spacer(modifier = Modifier.height(120.dp))
        Column (
            modifier = Modifier
//                .fillMaxHeight()
        ){
            Row (
//                verticalAlignment = Alignment.Bottom,
            ){
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {  },
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


//@Preview
//@Composable
//fun DisplayPageSearchPreview() {
//    SODV3203_CarRentalAppTheme {
//        Surface(
//            modifier = Modifier.fillMaxSize(),
//            color = MaterialTheme.colorScheme.background
//        ) {
//            DisplayPageSearch(
//                appUiState = AppUiState()
//            )
//        }
//    }
//}