package com.example.sodv3203_carrentalapp.ui

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
public fun tex(int: Int): String{
    return stringResource(id = int)
}

@Composable
fun DisplayPageLanding(
    appUiState: AppUiState,
    modifier: Modifier = Modifier,
    onSignOutButtonClicked: () -> Unit = {},
    onProfileButtonClicked: () -> Unit = {},
    onBookingButtonClicked: () -> Unit = {},
    onHistoryButtonClicked: () -> Unit = {},
    onSearchButtonClicked: () -> Unit = {},
    viewModel: AppViewModel

//    onQueryChange: (String) -> Unit,
//    onSearch: ((String) -> Unit),
//    isSearchActive: Boolean,
//    onActiveChanged: (Boolean) -> Unit,
) {

    val viewModel = viewModel
    val searchText by viewModel.searchText.collectAsState()
    val isSearching by viewModel.isSearching.collectAsState()
    val carList by viewModel.carList.collectAsState()


    var searchHistory = remember { mutableStateListOf("") }
    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) } // Active state for SearchBar

//    SearchBar(
//        query = searchText,
//        onQueryChange = viewModel::onSearchTextChange,
//        onSearch = viewModel::onSearchTextChange,
//        active = isSearching,
//        onActiveChange = { viewModel.onToogleSearch() } ,
//        modifier = Modifier.fillMaxWidth(),
//        placeholder = {
//            Text(text = "Enter keyword here")
//        },
//        trailingIcon = {
//            Icon(imageVector = Icons.Default.Search, contentDescription = null)
//        }) {
//        LazyColumn {
//            items(carList) { car ->
//                Text(
//                    text = stringResource(id = car.name),
//                    modifier = Modifier.padding(
//                        start = 8.dp,
//                        top = 4.dp,
//                        end = 8.dp,
//                        bottom = 4.dp
//                    )
//                )
//            }
//        }
//
//
//    }}


    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.errorContainer)
//            .fillMaxWidth()
            .fillMaxSize()
    ) {
//        Text(text = "Placeholder: Landing Page")
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .height(34.dp)
                .padding(start = 14.dp, end = 14.dp, top = 14.dp)
        ){

        Image(painter = painterResource(id = R.drawable.user), contentDescription = null )
        Spacer(modifier = Modifier.weight(0.5f))
        Image(painter = painterResource(id = R.drawable.bell), contentDescription = null )
        }

        TextField(

            value = text,
            onValueChange = { text = it },
            label = { Text("Search") },
            modifier = Modifier
                .padding(top = 16.dp)
                .height(26.dp)
                .width(340.dp)
                .align(Alignment.CenterHorizontally)
                .background(MaterialTheme.colorScheme.inversePrimary)
        )
            Row (
                modifier = Modifier
//                    .weight(0.4f)
                    .fillMaxWidth()
                    .height(440.dp)
                    .padding(top = 16.dp)
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
                    items(cars){
                        Image(

                            painter = painterResource( id = it.imageResourceId),
                            contentDescription = null ,
                            modifier = Modifier
                                .background(MaterialTheme.colorScheme.onPrimary)
                                .border(3.dp, Color.White, shape = RoundedCornerShape(8.dp) )
                                .height(100.dp)
                                .width(160.dp)
                                .padding( end = 4.dp)
                                .clickable {  }
                                )
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
                .height(50.dp)
                .fillMaxWidth()
                .padding(top = 30.dp)

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
                    .clickable { onSearchButtonClicked() }
            )
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = null,
                modifier = Modifier
                    .clickable { onProfileButtonClicked() }
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



