package com.example.sodv3203_carrentalapp.ui

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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sodv3203_carrentalapp.R
import com.example.sodv3203_carrentalapp.data.Car
import com.example.sodv3203_carrentalapp.data.WelcomeImage
import com.example.sodv3203_carrentalapp.ui.theme.SODV3203_CarRentalAppTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay


@OptIn(ExperimentalPagerApi::class)
@Composable
fun DisplayPageLanding(
    appUiState: AppUiState,
    onLandingButtonClicked: () -> Unit,
    onProfileButtonClicked: () -> Unit,
    onHistoryButtonClicked: () -> Unit,
    onSearchButtonClicked: (Car) -> Unit,
    onAddNewCarButtonClicked: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AppViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {

    val listAllCars by viewModel.listAllCars.collectAsState()

    val welcomeSlide: List<WelcomeImage> = appUiState.welcomeImageSlid
    val pagerState = rememberPagerState( welcomeSlide.size)

    LaunchedEffect(Unit) {
        while (true) {
            delay(3000)
            val nextPage = (pagerState.currentPage + 1)%pagerState.pageCount
            pagerState.scrollToPage(nextPage)
        }
    }

    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        Row(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .height(440.dp)
                .padding(top = 8.dp)

        ) {
            HorizontalPager( state = pagerState, count = welcomeSlide.size)
            { page ->
                Image(
                    painter =  painterResource(id =  welcomeSlide[page].imageResourceId) ,
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            LazyRow {
                items(listAllCars) {
                    Image(
                        painter = painterResource(id = it.imageResourceId),
                        contentDescription = null,
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
            onAddNewCarButtonClicked = onAddNewCarButtonClicked,
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
    onAddNewCarButtonClicked: () -> Unit,
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
        Image(
            painter = painterResource(id = R.drawable.home),
            contentDescription = null,
            modifier = Modifier
                .clickable { onLandingButtonClicked() }
                .fillMaxHeight()
        )
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = null,
            modifier = Modifier
                .clickable { onProfileButtonClicked() }
                .fillMaxHeight()
        )
        Image(
            painter = painterResource(id = R.drawable.history),
            contentDescription = null,
            modifier = Modifier
                .clickable { onHistoryButtonClicked() }
                .fillMaxHeight()
        )
        Image(
            painter = painterResource(id = R.drawable.icon_addcar),
            contentDescription = null,
            modifier = Modifier
                .clickable { onAddNewCarButtonClicked() }
                .fillMaxHeight()
        )
    }
}


@Preview(showBackground = true, heightDp = 800)
@Composable
fun DisplayPageLandingPreview() {
    SODV3203_CarRentalAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            DisplayPageLanding(
                appUiState = AppUiState(),
                onLandingButtonClicked = {},
                onProfileButtonClicked = {},
                onHistoryButtonClicked = {},
                onSearchButtonClicked = {},
                onAddNewCarButtonClicked = {},
            )
        }
    }
}



