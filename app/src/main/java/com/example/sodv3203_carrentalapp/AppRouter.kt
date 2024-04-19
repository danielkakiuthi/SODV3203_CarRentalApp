package com.example.sodv3203_carrentalapp

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.twotone.ExitToApp
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.sodv3203_carrentalapp.data.User
import com.example.sodv3203_carrentalapp.ui.AppUiState
import com.example.sodv3203_carrentalapp.ui.AppViewModel
import com.example.sodv3203_carrentalapp.ui.AppViewModelProvider
import com.example.sodv3203_carrentalapp.ui.DisplayPageAddCar
import com.example.sodv3203_carrentalapp.ui.DisplayPageBooking
import com.example.sodv3203_carrentalapp.ui.DisplayPageFinalReservationDetails
import com.example.sodv3203_carrentalapp.ui.DisplayPageHistory
import com.example.sodv3203_carrentalapp.ui.DisplayPageLanding
import com.example.sodv3203_carrentalapp.ui.DisplayPageLogin
import com.example.sodv3203_carrentalapp.ui.DisplayPageProfile
import com.example.sodv3203_carrentalapp.ui.DisplayPageSearch
import com.example.sodv3203_carrentalapp.ui.DisplayPageSignUp
import com.example.sodv3203_carrentalapp.ui.DisplayPageSummary
import com.example.sodv3203_carrentalapp.ui.theme.SODV3203_CarRentalAppTheme
import kotlinx.coroutines.launch


enum class PageTypes(@StringRes val title: Int) {
    Login(title = R.string.page_login_name),
    SignUp(title = R.string.page_signup_name),
    Landing(title = R.string.page_landing_name),
    Profile(title = R.string.page_profile_name),
    AddNewCar(title = R.string.page_add_new_car),
    Booking(title = R.string.page_booking_name),
    History(title = R.string.page_history_name),
    Search(title = R.string.page_search_name),
    FinalReservationDetails(title=R.string.page_final_reservation_details_name),
    Summary(title = R.string.page_summary_name)
}


@Composable
fun CarRentalAppBar(
    currentPage: PageTypes,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = { Text(stringResource(id = currentPage.title)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier
            .padding(0.dp)
            .height(30.dp),
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.button_back)
                    )
                }
            }
        }
    )
}



@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun CarRentalApp(
    viewModel: AppViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navController : NavHostController = rememberNavController()
    ) {

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentPage = PageTypes.valueOf(backStackEntry?.destination?.route ?: PageTypes.Login.name)
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            if (uiState.isUserLoggedIn) {
                Column {
                    CarRentalAppBar(
                        currentPage = currentPage,
                        canNavigateBack = navController.previousBackStackEntry != null,
                        navigateUp = { navController.navigateUp() }
                    )
                    TopHeaderLoggedIn(
                        appUiState = uiState,
                        onProfileButtonClicked = { navController.navigate(PageTypes.Profile.name) },
                        onSignOutButtonClicked = {
                            viewModel.signout()
                            navController.navigate(PageTypes.Login.name)
                        }
                    )
                }
            }
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = PageTypes.Login.name,
            modifier = Modifier.padding(innerPadding)
        ) {

            composable(route = PageTypes.Login.name) {
                val context = LocalContext.current.applicationContext
                DisplayPageLogin(
                    onSignUpButtonClicked = { navController.navigate(PageTypes.SignUp.name) },
                    onLoginButtonClicked = { isAuthenticated, verifiedUser ->
                        if (isAuthenticated) {
                            viewModel.updateLoggedUser(verifiedUser)
                            Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
                            navController.navigate(PageTypes.Landing.name)
                        }
                        else {
                            Toast.makeText(context, "Invalid Credentials", Toast.LENGTH_SHORT).show()
                        }
                    },
                    modifier = Modifier
                        .fillMaxSize()
                )
            }

            composable(route = PageTypes.SignUp.name) {
                val context = LocalContext.current.applicationContext
                val coroutineScope = rememberCoroutineScope()
                DisplayPageSignUp(
                    onRegisterUserButtonClicked = { newUser ->
                        if(
                            newUser.username.isEmpty() or
                            newUser.password.isEmpty() or
                            newUser.firstName.isEmpty() or
                            newUser.lastName.isEmpty() or
                            newUser.birthDate.isEmpty() or
                            newUser.phone.isEmpty() or
                            newUser.email.isEmpty()
                        ){
                            Toast.makeText(context, "[ERROR] New User not added to database. Verify user fields.", Toast.LENGTH_SHORT).show()
                        }
                        else {
                            coroutineScope.launch {
                                Toast.makeText(context, "Adding new user to database...", Toast.LENGTH_SHORT).show()
                                viewModel.addUserInDatabase(newUser)
                                navController.navigate(PageTypes.Login.name)
                            }
                        }
                    },
                    onCancelButtonClicked = { navController.navigate(PageTypes.Login.name) },
                    modifier = Modifier
                        .fillMaxSize()
                )
            }

            composable(route = PageTypes.Landing.name) {
                DisplayPageLanding(
                    appUiState = uiState,
                    onLandingButtonClicked = { navController.navigate(PageTypes.Landing.name) },
                    onProfileButtonClicked = { navController.navigate(PageTypes.Profile.name) },
                    onHistoryButtonClicked = { navController.navigate(PageTypes.History.name) },
                    onSearchButtonClicked = {
                        viewModel.updateSelectedCar(it)
                        navController.navigate(PageTypes.Search.name)
                    },
                    onAddNewCarButtonClicked = { navController.navigate(PageTypes.AddNewCar.name) },
                    modifier = Modifier
                        .fillMaxSize()
                    )
            }

            composable(route = PageTypes.Profile.name) {
                val context = LocalContext.current.applicationContext
                val coroutineScope = rememberCoroutineScope()
                DisplayPageProfile(
                    appUiState = uiState,
                    onUpdateButtonClicked = { currentLoggedUser ->
                        if (
                            currentLoggedUser.username.isEmpty() or
                            currentLoggedUser.password.isEmpty() or
                            currentLoggedUser.firstName.isEmpty() or
                            currentLoggedUser.lastName.isEmpty() or
                            currentLoggedUser.birthDate.isEmpty() or
                            currentLoggedUser.phone.isEmpty() or
                            currentLoggedUser.email.isEmpty()
                        ) {
                            Toast.makeText(context, "[ERROR] User not updated in database. Verify user fields.", Toast.LENGTH_SHORT).show()
                        }
                        else {
                            coroutineScope.launch {
                                Toast.makeText(context, "Updating user in database...", Toast.LENGTH_SHORT).show()
                                viewModel.updateUserInDatabase(currentLoggedUser)
                                navController.navigate(PageTypes.Landing.name)
                            }
                        }
                    },
                    onCancelButtonClicked = { navController.navigate(PageTypes.Landing.name) },
                    modifier = Modifier
                        .fillMaxSize()
                )
            }

            composable(route = PageTypes.AddNewCar.name) {
                val context = LocalContext.current.applicationContext
                val coroutineScope = rememberCoroutineScope()
                DisplayPageAddCar(
                    onAddNewCarButtonClicked = { newCar ->
                        if(
                            newCar.name.isEmpty() or
                            newCar.feature.isEmpty() or
                            newCar.category.isEmpty()
                        ){
                            Toast.makeText(context, "[ERROR] New Car not added to database. Verify car fields.", Toast.LENGTH_SHORT).show()
                        }
                        else {
                            coroutineScope.launch {
                                Toast.makeText(context, "Adding new car to database...", Toast.LENGTH_SHORT).show()
                                viewModel.addCarInDatabase(newCar)
                                navController.navigate(PageTypes.Landing.name)
                            }
                        }
                    },
                    onCancelButtonClicked = { navController.navigate(PageTypes.Landing.name) },
                    modifier = Modifier
                        .fillMaxSize()
                )
            }

            composable(route = PageTypes.Booking.name) {
                DisplayPageBooking(
                    appUiState = uiState,
                    onLandingButtonClicked = { navController.navigate(PageTypes.Landing.name) },
                    onProfileButtonClicked = { navController.navigate(PageTypes.Profile.name) },
                    onHistoryButtonClicked = { navController.navigate(PageTypes.History.name) },
                    onAddNewCarButtonClicked = { navController.navigate(PageTypes.AddNewCar.name) },
                    modifier = Modifier
                        .fillMaxSize()
                )
            }

            composable(route = PageTypes.History.name) {
                DisplayPageHistory(
                    appUiState = uiState,
                    onBackButtonClicked = { navController.navigate(PageTypes.Landing.name) },
                    onCardBookingClick = {reservation ->

                        viewModel.updateSelectedReservation(reservation)
                        viewModel.updateSelectedCarByCarId(reservation.carId)
                        navController.navigate(PageTypes.Booking.name)
                    },
                    modifier = Modifier
                        .fillMaxSize()
                )
            }

            composable(route = PageTypes.Search.name) {
                DisplayPageSearch(
                    appUiState = uiState,
                    onBackButtonClicked = { navController.navigate(PageTypes.Landing.name) },
                    onSelectButtonClicked = { navController.navigate(PageTypes.FinalReservationDetails.name) },
                    modifier = Modifier
                        .fillMaxSize()
                )
            }

            composable(route = PageTypes.FinalReservationDetails.name) {
                val context = LocalContext.current.applicationContext
                val coroutineScope = rememberCoroutineScope()
                DisplayPageFinalReservationDetails(
                    appUiState = uiState,
                    onBackButtonClicked = { navController.navigateUp() },
                    onConfirmButtonClicked = {currentReservation ->
                        if (
                            currentReservation.location.isEmpty() or
                            currentReservation.nameOnCard.isEmpty() or
                            currentReservation.cardNumber.isEmpty() or
                            currentReservation.cvc.isEmpty()
                        ) {
                            Toast.makeText(context, "[ERROR] New Reservation not added to database. Verify reservation fields.", Toast.LENGTH_SHORT).show()
                        }
                        else {
                            coroutineScope.launch {
                                Toast.makeText(context, "Adding new reservation to database...", Toast.LENGTH_SHORT).show()
                                viewModel.addReservationInDatabase(currentReservation)
                                viewModel.updateSelectedReservation(currentReservation)
                                navController.navigate(PageTypes.Summary.name)
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxSize()
                )
            }

            composable(route = PageTypes.Summary.name) {
                val context = LocalContext.current.applicationContext
                DisplayPageSummary(
                    appUiState = uiState,
                    onBackButtonClicked = { navController.navigateUp() },
                    onConfirmButtonClicked = {
                        Toast.makeText(context, "Reservation Successful", Toast.LENGTH_SHORT).show()
                        navController.navigate(PageTypes.Landing.name)
                    },
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }

    }
}


@Composable
fun TopHeaderLoggedIn(
    appUiState: AppUiState,
    onProfileButtonClicked: () -> Unit = {},
    onSignOutButtonClicked: () -> Unit = {},
) {
    val currentLoggedUser: User = appUiState.loggedUser ?: appUiState.placeholderUser
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(start = 15.dp, end = 15.dp, top = 14.dp, bottom = 15.dp)
    ){
        Image(
            painter = painterResource(id = R.drawable.user),
            contentDescription = "User",
            modifier = Modifier
                .clickable { onProfileButtonClicked() }
        )
        Text(text = "${currentLoggedUser.firstName} ${currentLoggedUser.lastName}", modifier=Modifier.padding(horizontal = 10.dp))
        Spacer(modifier = Modifier.weight(1f))
        Image(painter = painterResource(id = R.drawable.bell), contentDescription = "Notifications" )
        Button(
            onClick = onSignOutButtonClicked,
            contentPadding = PaddingValues(1.dp)
        ) {
            Image(imageVector = Icons.TwoTone.ExitToApp, contentDescription = "Logout", modifier = Modifier.height(50.dp) )
            Text(text = "Logout", fontSize = 13.sp)
        }
    }
    Divider(color = Color.Black, thickness = 2.dp, modifier = Modifier.padding(horizontal = 10.dp))
}


@Preview(showBackground = true, heightDp = 800)
@Composable
fun DisplayCarRentalAppPreview() {
    SODV3203_CarRentalAppTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            CarRentalApp()
        }
    }
}
