package com.example.sodv3203_carrentalapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.sodv3203_carrentalapp.R
import com.example.sodv3203_carrentalapp.data.AppUiState
import com.example.sodv3203_carrentalapp.ui.theme.SODV3203_CarRentalAppTheme

@Composable
fun DisplayPageLanding(
    appUiState: AppUiState,
    modifier: Modifier = Modifier,
    onSignOutButtonClicked: () -> Unit = {},
    onProfileButtonClicked: () -> Unit = {},
    onBookingButtonClicked: () -> Unit = {},
    onHistoryButtonClicked: () -> Unit = {},
    onSearchButtonClicked: () -> Unit = {}
) {

    Column(
        modifier = modifier
    ) {
        Text(text = "Placeholder: Landing Page")
        Row(
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { onProfileButtonClicked() }
                ) {
                    Text(stringResource(R.string.page_profile_name))
                }
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { onBookingButtonClicked() }
                ) {
                    Text(stringResource(R.string.page_booking_name))
                }
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { onHistoryButtonClicked() }
                ) {
                    Text(stringResource(R.string.page_history_name))
                }
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { onSearchButtonClicked() }
                ) {
                    Text(stringResource(R.string.page_search_name))
                }
                OutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { onSignOutButtonClicked() }
                ) {
                    Text(stringResource(R.string.button_signout))
                }
            }
        }
    }
}


@Preview
@Composable
fun DisplayPageLandingPreview() {
    SODV3203_CarRentalAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            DisplayPageLanding(
                appUiState = AppUiState()
            )
        }
    }
}