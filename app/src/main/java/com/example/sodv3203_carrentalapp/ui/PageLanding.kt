package com.example.sodv3203_carrentalapp.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PlainTooltipBox
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sodv3203_carrentalapp.R
import com.example.sodv3203_carrentalapp.data.AppUiState
import com.example.sodv3203_carrentalapp.data.Car
import com.example.sodv3203_carrentalapp.ui.theme.SODV3203_CarRentalAppTheme


@Composable
fun DisplayPageLanding(
    appUiState: AppUiState,
    modifier: Modifier = Modifier,
    onSignOutButtonClicked: () -> Unit = {},
    onLandingButtonClicked: () -> Unit = {},
    onProfileButtonClicked: () -> Unit = {},
    onBookingButtonClicked: () -> Unit = {},
    onHistoryButtonClicked: () -> Unit = {},
    onSearchButtonClicked: (Car) -> Unit = {}
) {

    val carsList: List<Car> = appUiState.listAllRegisteredCars


    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {



//        SearchBar()

        Row (
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .height(440.dp)
                .padding(top = 8.dp)
        ){
            Image(
                painter = painterResource(id = R.drawable.starter),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            LazyRow (){
                items(carsList){
                    Image(
                        painter = painterResource( id = it.imageResourceId),
                        contentDescription = null ,
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.onPrimary)
                            .border(3.dp, Color.White, shape = RoundedCornerShape(8.dp))
                            .height(100.dp)
                            .width(160.dp)
                            .padding(end = 4.dp)
                            .clickable {
                                onSearchButtonClicked(it)
                            }
                    )
                }
            }
        }

        BottomNavigationBar(
            onLandingButtonClicked = onLandingButtonClicked,
            onProfileButtonClicked = onProfileButtonClicked,
            onHistoryButtonClicked = onHistoryButtonClicked,
        )

    }
}


@Composable
private fun SearchBar() {
    var text by remember { mutableStateOf("") }
    Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Search") },
            modifier = Modifier
                .padding(top = 16.dp)
                .height(26.dp)
                .width(340.dp)
                .background(MaterialTheme.colorScheme.inversePrimary)
        )
    }
}


@Composable
fun BottomNavigationBar(
    onLandingButtonClicked: () -> Unit,
    onProfileButtonClicked: () -> Unit,
    onHistoryButtonClicked: () -> Unit,
) {
    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .height(80.dp)
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(vertical = 20.dp)
    ){
        PlainTooltipBox(
            tooltip = { Text("Add to favorites" ) },
            contentColor = White
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


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true, heightDp = 800)
@Composable
fun DisplayPageLandingPreview() {
    SODV3203_CarRentalAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            DisplayPageLanding(
                appUiState = AppUiState(),
                onSearchButtonClicked = {}
            )
        }
    }
}



