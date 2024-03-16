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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sodv3203_carrentalapp.R
import com.example.sodv3203_carrentalapp.data.AppUiState
import com.example.sodv3203_carrentalapp.data.Car
import com.example.sodv3203_carrentalapp.data.cars
import com.example.sodv3203_carrentalapp.ui.theme.SODV3203_CarRentalAppTheme

@Composable
fun DisplayPageLanding(
    appUiState: AppUiState,
    modifier: Modifier = Modifier,
    onSignOutButtonClicked: () -> Unit = {},
    onProfileButtonClicked: () -> Unit = {},
    onBookingButtonClicked: () -> Unit = {},
    onHistoryButtonClicked: () -> Unit = {},
    onSearchButtonClicked: () -> Unit = {},

//    onQueryChange: (String) -> Unit,
//    onSearch: ((String) -> Unit),
//    isSearchActive: Boolean,
//    onActiveChanged: (Boolean) -> Unit,
) {


    Column(
        modifier = modifier
    ) {
//        Text(text = "Placeholder: Landing Page")
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .height(34.dp)
        ){

        Image(painter = painterResource(id = R.drawable.user), contentDescription = null )
        Spacer(modifier = Modifier.weight(0.5f))
        Image(painter = painterResource(id = R.drawable.bell), contentDescription = null )
        }



        Column (
            modifier = Modifier
//                .fillMaxHeight()
//                .padding( top = 20.dp)
        ){
            var text by remember { mutableStateOf("") }
            TextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Search") },
                modifier = Modifier
                    .padding(top = 16.dp)
                    .height(24.dp)
                    .width(340.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Row (
                modifier = Modifier
//                    .weight(0.4f)
                    .fillMaxWidth()
                    .height(440.dp)
                    .padding( top = 16.dp)
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
//                    .size( 180.dp)
                    .height(100.dp)
                    .fillMaxWidth()
            ) {
                LazyRow (){
                    items(cars){
                        Image(painter = painterResource(id = it.imageResourceId) , contentDescription = null )
//                        Image(painter = Car, contentDescription = )
                    }
                }
            }
        }

//        var searchQuery by rememberSaveable { mutableStateOf("") }
//        SearchBar(
//            query = searchQuery ,
//            onQueryChange = { query ->
//                searchQuery = query
//                onQueryChange(query)
//            },
//            onSearch = onSearch,
//            active = isSearchActive,
//            onActiveChange = onActiveChanged) {
//
//        }
//        Row(
//            modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))
//        ) {
//
//            Column(
//                verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
//            ) {
//
//                LazyRow (){
//                    items(cars){
//                        Image(painter = painterResource(id = it.imageResourceId) , contentDescription = null )
////                        Image(painter = Car, contentDescription = )
//                    }
//                }
//
//
//            }
//        }

        Row (
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .height( 50.dp)
                .fillMaxWidth()
                .padding( top = 30.dp)

        ){
            Image(
                painter = painterResource(id = R.drawable.home),
                contentDescription = null,
                modifier = Modifier
                    .clickable { onSignOutButtonClicked() }
            )
            Image(
                painter = painterResource(id = R.drawable.booking),
                contentDescription = null,
                modifier = Modifier
                    .clickable { onSignOutButtonClicked() }
            )
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = null,
                modifier = Modifier
                    .clickable { onSignOutButtonClicked() }
            )
            Image(
                painter = painterResource(id = R.drawable.history),
                contentDescription = null,
                modifier = Modifier
                    .clickable { onSignOutButtonClicked() }
            )
        }

//        Button(
//            modifier = Modifier.fillMaxWidth(),
//            onClick = { onProfileButtonClicked() }
//        ) {
//            Text(stringResource(R.string.page_profile_name))
//        }
//        Button(
//            modifier = Modifier.fillMaxWidth(),
//            onClick = { onBookingButtonClicked() }
//        ) {
//            Text(stringResource(R.string.page_booking_name))
//        }
//        Button(
//            modifier = Modifier.fillMaxWidth(),
//            onClick = { onHistoryButtonClicked() }
//        ) {
//            Text(stringResource(R.string.page_history_name))
//        }
//        Button(
//            modifier = Modifier.fillMaxWidth(),
//            onClick = { onSearchButtonClicked() }
//        ) {
//            Text(stringResource(R.string.page_search_name))
//        }
//        OutlinedButton(
//            modifier = Modifier.fillMaxWidth(),
//            onClick = { onSignOutButtonClicked() }
//        ) {
//            Text(stringResource(R.string.button_signout))
//        }
    }
}


//@Preview
//@Composable
//fun DisplayPageLandingPreview() {
//    SODV3203_CarRentalAppTheme {
//        Surface(
//            modifier = Modifier.fillMaxSize(),
//            color = MaterialTheme.colorScheme.background
//        ) {
//            DisplayPageLanding(
//                appUiState = AppUiState()
//            )
//        }
//    }
//}