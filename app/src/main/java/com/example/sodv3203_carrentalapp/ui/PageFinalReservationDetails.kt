package com.example.sodv3203_carrentalapp.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerFormatter
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sodv3203_carrentalapp.R
import com.example.sodv3203_carrentalapp.data.AppUiState
import com.example.sodv3203_carrentalapp.data.Reservation
import com.example.sodv3203_carrentalapp.ui.theme.SODV3203_CarRentalAppTheme
import java.util.Date


@Composable
fun DisplayPageFinalReservationDetails(
    appUiState: AppUiState,
    modifier: Modifier = Modifier,
    onBackButtonClicked: () -> Unit = {},
    onConfirmButtonClicked: (Reservation) -> Unit = {}
) {

    val datePickerStateStart = rememberDatePickerState(initialDisplayMode = DisplayMode.Input)
    val datePickerStateEnd = rememberDatePickerState(initialDisplayMode = DisplayMode.Input)
    val focusManager = LocalFocusManager.current


    var currentReservation: Reservation = remember{Reservation(
        id = appUiState.listAllReservations.last().id + 1,
        user = appUiState.loggedUser ?: appUiState.placeholderUser,
        car = appUiState.selectedCar ?: appUiState.placeholderCar,
        location = "",
        startDate = Date(datePickerStateStart.selectedDateMillis?:0),
        endDate = Date(datePickerStateEnd.selectedDateMillis?:0),
        pricePerDay = 50f,
        additionalRequests = "",
        nameOnCard = "",
        cardNumber = "",
        cvc = ""
    )}

    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(15.dp)
    ) {

        Column {
            Text(
                text = "Complete Reservation",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )
            OutlinedTextField(
                placeholder = { Text("123 Ave SW") },
                value = currentReservation.location,
                singleLine = true,
                shape = MaterialTheme.shapes.large,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                    disabledContainerColor = MaterialTheme.colorScheme.surface,
                ),
                onValueChange = { currentReservation.location=it },
                label = {
                    Text("Pickup Location")
                },
                isError = false,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                ),
                trailingIcon = {
                    Icon(imageVector = Icons.Filled.LocationOn, contentDescription = "From Date")
                }
            )
            DatePicker(
                state = datePickerStateStart,
                modifier = Modifier.padding(horizontal = 16.dp),
                title = {
                    Text("From Date")
                },
                headline = {
                    DatePickerDefaults.DatePickerHeadline(
                        state = datePickerStateStart,
                        dateFormatter = DatePickerFormatter(),
                        modifier = Modifier.padding(0.dp)
                    )
                }
            )

            DatePicker(
                state = datePickerStateEnd,
                modifier = Modifier.padding(horizontal = 16.dp),
                title = {
                    Text("To Date")
                },
                headline = {
                    DatePickerDefaults.DatePickerHeadline(
                        state = datePickerStateEnd,
                        dateFormatter = DatePickerFormatter(),
                        modifier = Modifier.padding(0.dp)
                    )
                }
            )
        }

        Column {
            Text(text = "Additional Requests", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            OutlinedTextField(
                placeholder = { Text("Eg. car model, color, etc") },
                value = currentReservation.additionalRequests,
                singleLine = false,
                minLines = 4,
                maxLines = 4,
                shape = MaterialTheme.shapes.large,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                    disabledContainerColor = MaterialTheme.colorScheme.surface,
                ),
                onValueChange = { currentReservation.additionalRequests=it },
                label = {
                    Text("Additional Requests")
                },
                isError = false,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                )
            )
        }


        Column {
            Text(text = "Payment", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            OutlinedTextField(
                placeholder = { Text("John Doe") },
                value = currentReservation.nameOnCard,
                singleLine = true,
                shape = MaterialTheme.shapes.large,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                    disabledContainerColor = MaterialTheme.colorScheme.surface,
                ),
                onValueChange = { currentReservation.nameOnCard=it },
                label = {
                    Text("Name on Card")
                },
                isError = false,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                )
            )
            OutlinedTextField(
                placeholder = { Text("1111 2222 3333 4444") },
                value = currentReservation.cardNumber,
                singleLine = true,
                shape = MaterialTheme.shapes.large,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                    disabledContainerColor = MaterialTheme.colorScheme.surface,
                ),
                onValueChange = { currentReservation.cardNumber=it },
                label = {
                    Text("Card Number")
                },
                isError = false,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                )
            )
            OutlinedTextField(
                placeholder = { Text("123") },
                value = currentReservation.cvc,
                singleLine = true,
                shape = MaterialTheme.shapes.large,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                    disabledContainerColor = MaterialTheme.colorScheme.surface,
                ),
                onValueChange = { currentReservation.cvc=it },
                label = {
                    Text("CVC")
                },
                isError = false,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { onConfirmButtonClicked(currentReservation) }
                )
            )
        }



        Row(
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { onConfirmButtonClicked(currentReservation) }
                ) {
                    Text(stringResource(R.string.button_complete_reservation))
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
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true, heightDp = 1800)
@Composable
fun DisplayPageFinalReservationDetailsPreview() {
    SODV3203_CarRentalAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            DisplayPageFinalReservationDetails(
                appUiState = AppUiState()
            )
        }
    }
}