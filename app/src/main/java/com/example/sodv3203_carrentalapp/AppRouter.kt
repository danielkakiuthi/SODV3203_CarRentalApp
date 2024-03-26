package com.example.sodv3203_carrentalapp

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

import com.example.sodv3203_carrentalapp.ui.AppViewModel
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


enum class PageTypes(@StringRes val title: Int) {
    Login(title = R.string.page_login_name),
    SignUp(title = R.string.page_signup_name),
    Landing(title = R.string.page_landing_name),
    Profile(title = R.string.page_profile_name),
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
    TopAppBar(
        title = { Text(stringResource(id = currentPage.title)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
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


@Composable
fun CarRentalApp(
    viewModel: AppViewModel = viewModel(),
    navController : NavHostController = rememberNavController()
    ) {

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentPage = PageTypes.valueOf(backStackEntry?.destination?.route ?: PageTypes.Login.name)

    Scaffold(
//        topBar = {
//            CarRentalAppBar(
//                currentPage = currentPage,
//                canNavigateBack = navController.previousBackStackEntry != null,
//                navigateUp = { navController.navigateUp() }
//            )
//        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()
        NavHost(
            navController = navController,
            startDestination = PageTypes.Login.name,
            modifier = Modifier.padding(innerPadding)
        ) {


            composable(route = PageTypes.Login.name) {
                val context = LocalContext.current.applicationContext
                DisplayPageLogin(
                    appUiState = uiState,
                    viewModel = viewModel,
                    onSignUpButtonClicked = { navController.navigate(PageTypes.SignUp.name) },
                    onLoginButtonClicked = { username, password ->
                        if (viewModel.authenticate(username, password)) {
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
                DisplayPageSignUp(
                    appUiState = uiState,
                    viewModel = viewModel,
                    onRegisterUserButtonClicked = { username, password, firstname, lastname, birthdate, phone, email ->
                        Toast.makeText(context, "Signup Successful", Toast.LENGTH_SHORT).show()
                        navController.navigate(PageTypes.Landing.name)
                    },
                    onCancelButtonClicked = { navController.navigate(PageTypes.Login.name) },
                    modifier = Modifier
                        .fillMaxSize()
                )
            }

            composable(route = PageTypes.Landing.name) {
                DisplayPageLanding(
                    appUiState = uiState,
                    onSignOutButtonClicked = {
                        viewModel.signout()
                        navController.navigate(PageTypes.Login.name)
                    },
                    onLandingButtonClicked = { navController.navigate(PageTypes.Landing.name) },
                    onProfileButtonClicked = { navController.navigate(PageTypes.Profile.name) },
                    onBookingButtonClicked = { navController.navigate(PageTypes.Booking.name) },
                    onHistoryButtonClicked = { navController.navigate(PageTypes.History.name) },
                    onSearchButtonClicked = {
                        viewModel.updateSelectedCar(it)
                        navController.navigate(PageTypes.Search.name)
                    },
                    modifier = Modifier
                        .fillMaxSize()
                    )
            }

            composable(route = PageTypes.Profile.name) {
                val context = LocalContext.current.applicationContext
                DisplayPageProfile(
                    appUiState = uiState,
                    viewModel = viewModel,
                    onUpdateButtonClicked = { username, password, firstname, lastname, birthdate, phone, email ->
                        Toast.makeText(context, "Update Successful", Toast.LENGTH_SHORT).show()
                        navController.navigate(PageTypes.Profile.name)
                    },
                    onCancelButtonClicked = { navController.navigate(PageTypes.Landing.name) },
                    modifier = Modifier
                        .fillMaxSize()
                )
            }

            composable(route = PageTypes.Booking.name) {
                DisplayPageBooking(
                    appUiState = uiState,
                    onSignOutButtonClicked = {
                        viewModel.signout()
                        navController.navigate(PageTypes.Login.name)
                    },
                    onLandingButtonClicked = { navController.navigate(PageTypes.Landing.name) },
                    onProfileButtonClicked = { navController.navigate(PageTypes.Profile.name) },
                    onBookingButtonClicked = { navController.navigate(PageTypes.Booking.name) },
                    onHistoryButtonClicked = { navController.navigate(PageTypes.History.name) },
                    onSearchButtonClicked = {
                        viewModel.updateSelectedCar(it)
                        navController.navigate(PageTypes.Search.name)
                    },
                    modifier = Modifier
                        .fillMaxSize()
                )
            }

            composable(route = PageTypes.History.name) {
                DisplayPageHistory(
                    appUiState = uiState,
                    onBackButtonClicked = { navController.navigate(PageTypes.Landing.name) },
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
                DisplayPageFinalReservationDetails(
                    appUiState = uiState,
                    onBackButtonClicked = { navController.navigate(PageTypes.Search.name) },
                    onConfirmButtonClicked = { navController.navigate(PageTypes.Summary.name) },
                    modifier = Modifier
                        .fillMaxSize()
                )
            }

            composable(route = PageTypes.Summary.name) {
                DisplayPageSummary(
                    appUiState = uiState,
                    onBackButtonClicked = { navController.navigate(PageTypes.Search.name) },
                    onConfirmButtonClicked = { navController.navigate(PageTypes.Landing.name) },
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }

    }

}



@Preview(showBackground = true, heightDp = 800)
@Composable
fun DisplayCarRentalAppPreview() {
    SODV3203_CarRentalAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            CarRentalApp()
        }
    }
}
