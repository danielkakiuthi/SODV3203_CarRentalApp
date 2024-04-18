package com.example.sodv3203_carrentalapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import com.example.sodv3203_carrentalapp.R
import com.example.sodv3203_carrentalapp.data.User
import com.example.sodv3203_carrentalapp.ui.theme.SODV3203_CarRentalAppTheme

@Composable
fun DisplayPageProfile(
    appUiState: AppUiState,
    onUpdateButtonClicked: (currentLoggedUser: User) -> Unit,
    onCancelButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {

    var currentLoggedUser: User = appUiState.loggedUser ?: appUiState.placeholderUser

    val id by remember { mutableIntStateOf(currentLoggedUser.id) }
    var username by remember { mutableStateOf(currentLoggedUser.username) }
    var password by remember { mutableStateOf(currentLoggedUser.password) }
    var firstName by remember { mutableStateOf(currentLoggedUser.firstName) }
    var lastName by remember { mutableStateOf(currentLoggedUser.lastName) }
    var birthDate by remember { mutableStateOf(currentLoggedUser.birthDate) }
    var phone by remember { mutableStateOf(currentLoggedUser.phone) }
    var email by remember { mutableStateOf(currentLoggedUser.email) }


    Column(
        modifier = modifier
            .fillMaxSize()
            .fillMaxWidth()
            .padding(horizontal = 26.dp, vertical = 70.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "My Profile", fontSize = 30.sp)
        Spacer(modifier = Modifier.padding(bottom = 20.dp))

        val focusManager = LocalFocusManager.current

        OutlinedTextField(
            placeholder = { Text(text = "Usernames") },
            value = username,
            onValueChange = { username = it },
            label = { Text(text = "Username")},
            enabled = false,
            shape = RoundedCornerShape(20.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            ),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Person, contentDescription = "Username")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
            ,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            )
        )

        OutlinedTextField(
            placeholder = { Text(text = "Password") },
            value = password,
            onValueChange = { password = it },
            label = { Text(text = "Password")},
            shape = RoundedCornerShape(20.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            ),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Lock, contentDescription = "Password")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Go
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            )
        )

        OutlinedTextField(
            placeholder = { Text(text = "Firstname") },
            value = firstName,
            onValueChange = { validFirstName ->
                firstName = validFirstName.filter { !it.isDigit() } },
            label = { Text(text = "Firstname")},
            shape = RoundedCornerShape(20.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            ),
            leadingIcon = {
                Icon(imageVector = Icons.Default.AccountBox, contentDescription = "Firstname")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
            ,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            )
        )

        OutlinedTextField(
            placeholder = { Text(text = "Lastname") },
            value = lastName,
            onValueChange = { validLastName ->
                lastName = validLastName.filter { !it.isDigit() } },
            label = { Text(text = "Lastname")},
            shape = RoundedCornerShape(20.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            ),
            leadingIcon = {
                Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "Lastname")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
            ,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            )
        )

        OutlinedTextField(
            placeholder = { Text(text = "Birthdate") },
            value = birthDate,
            onValueChange = { birthDate = it },
            label = { Text(text = "Birthdate")},
            shape = RoundedCornerShape(20.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            ),
            leadingIcon = {
                Icon(imageVector = Icons.Default.DateRange, contentDescription = "Birthdate")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
            ,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            )
        )

        OutlinedTextField(
            placeholder = { Text(text = "Phone number") },
            value = phone,
            onValueChange = { if (it.isDigitsOnly()) phone = it },
            label = { Text(text = "Phone number")},
            shape = RoundedCornerShape(20.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            ),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Phone, contentDescription = "Phone number")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Go
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            )
        )

        OutlinedTextField(
            placeholder = { Text(text = "Email") },
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "Email")},
            shape = RoundedCornerShape(20.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            ),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Email, contentDescription = "Email")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
            ,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Go
            ),
            keyboardActions = KeyboardActions(
                onGo = { updateUser(
                    id = id,
                    username = username,
                    password = password,
                    firstName = firstName,
                    lastName = lastName,
                    birthDate = birthDate,
                    phone = phone,
                    email = email,
                    onUpdateButtonClicked = onUpdateButtonClicked
                ) }
            )
        )

        Row(
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
            ) {
                Button(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = { updateUser(
                        id = id,
                        username = username,
                        password = password,
                        firstName = firstName,
                        lastName = lastName,
                        birthDate = birthDate,
                        phone = phone,
                        email = email,
                        onUpdateButtonClicked = onUpdateButtonClicked
                    ) }
                ) {
                    Text(stringResource(R.string.button_update_user), fontSize = 22.sp)
                }

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { onCancelButtonClicked() }
                ) {
//                    Text(stringResource(R.string.button_cancel), fontSize = 22.sp)
                    Text(stringResource(R.string.button_back), fontSize = 22.sp)
                }
            }
        }
    }
}

fun updateUser(
    id: Int,
    username: String,
    password :String,
    firstName :String,
    lastName :String,
    birthDate :String,
    phone :String,
    email :String,
    onUpdateButtonClicked: (User) -> Unit
) {
    val updatedUser = User(
        id = id,
        username = username,
        password = password,
        firstName = firstName,
        lastName = lastName,
        birthDate = birthDate,
        phone = phone,
        email = email
    )
    onUpdateButtonClicked(updatedUser)
}

@Preview(showBackground = true, heightDp = 800)
@Composable
fun DisplayPageProfilePreview() {
    SODV3203_CarRentalAppTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxWidth()
        ) {
            DisplayPageProfile(
                appUiState = AppUiState(),
                onUpdateButtonClicked = {},
                onCancelButtonClicked = {}
            )
        }
    }
}