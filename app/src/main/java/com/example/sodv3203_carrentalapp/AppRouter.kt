package com.example.sodv3203_carrentalapp

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

import com.example.sodv3203_carrentalapp.ui.AppViewModel
import com.example.sodv3203_carrentalapp.ui.DisplayPageBooking
import com.example.sodv3203_carrentalapp.ui.DisplayPageHistory
import com.example.sodv3203_carrentalapp.ui.DisplayPageLanding
import com.example.sodv3203_carrentalapp.ui.DisplayPageLogin
import com.example.sodv3203_carrentalapp.ui.DisplayPageProfile
import com.example.sodv3203_carrentalapp.ui.DisplayPageSearch
import com.example.sodv3203_carrentalapp.ui.DisplayPageSignUp
import com.example.sodv3203_carrentalapp.ui.DisplayPageSummary


enum class PageTypes(@StringRes val title: Int) {
    Login(title = R.string.page_login_name),
    SignUp(title = R.string.page_signup_name),
    Landing(title = R.string.page_landing_name),
    Profile(title = R.string.page_profile_name),
    Booking(title = R.string.page_booking_name),
    History(title = R.string.page_history_name),
    Search(title = R.string.page_search_name),
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
        topBar = {
            CarRentalAppBar(
                currentPage = currentPage,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()
        NavHost(
            navController = navController,
            startDestination = PageTypes.Login.name,
            modifier = Modifier.padding(innerPadding)
        ) {

            composable(route = PageTypes.Login.name) {
                DisplayPageLogin(
                    appUiState = uiState,
                    onSignUpButtonClicked = { navController.navigate(PageTypes.SignUp.name) },
                    onLoginButtonClicked = { navController.navigate(PageTypes.Landing.name) },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(R.dimen.padding_medium))
                )
            }

            composable(route = PageTypes.SignUp.name) {
                DisplayPageSignUp(
                    appUiState = uiState,
                    onRegisterUserButtonClicked = { navController.navigate(PageTypes.Login.name) },
                    onCancelButtonClicked = { navController.navigate(PageTypes.Login.name) },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(R.dimen.padding_medium))
                )
            }

            composable(route = PageTypes.Landing.name) {
                DisplayPageLanding(
                    appUiState = uiState,
                    onSignOutButtonClicked = { navController.navigate(PageTypes.Login.name) },
                    onProfileButtonClicked = { navController.navigate(PageTypes.Profile.name) },
                    onBookingButtonClicked = { navController.navigate(PageTypes.Booking.name) },
                    onHistoryButtonClicked = { navController.navigate(PageTypes.History.name) },
                    onSearchButtonClicked = { navController.navigate(PageTypes.Search.name) },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(R.dimen.padding_medium))
                )
            }

            composable(route = PageTypes.Profile.name) {
                DisplayPageProfile(
                    appUiState = uiState,
                    onBackButtonClicked = { navController.navigate(PageTypes.Landing.name) },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(R.dimen.padding_medium))
                )
            }

            composable(route = PageTypes.Booking.name) {
                DisplayPageBooking(
                    appUiState = uiState,
                    onBackButtonClicked = { navController.navigate(PageTypes.Landing.name) },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(R.dimen.padding_medium))
                )
            }

            composable(route = PageTypes.History.name) {
                DisplayPageHistory(
                    appUiState = uiState,
                    onBackButtonClicked = { navController.navigate(PageTypes.Landing.name) },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(R.dimen.padding_medium))
                )
            }

            composable(route = PageTypes.Search.name) {
                DisplayPageSearch(
                    appUiState = uiState,
                    onBackButtonClicked = { navController.navigate(PageTypes.Landing.name) },
                    onSelectButtonClicked = { navController.navigate(PageTypes.Summary.name) },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(R.dimen.padding_medium))
                )
            }

            composable(route = PageTypes.Summary.name) {
                DisplayPageSummary(
                    appUiState = uiState,
                    onBackButtonClicked = { navController.navigate(PageTypes.Search.name) },
                    onConfirmButtonClicked = { navController.navigate(PageTypes.Landing.name) },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(R.dimen.padding_medium))
                )
            }
        }

    }

}