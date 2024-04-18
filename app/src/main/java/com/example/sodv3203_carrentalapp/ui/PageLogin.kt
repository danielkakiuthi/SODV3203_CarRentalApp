package com.example.sodv3203_carrentalapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sodv3203_carrentalapp.R
import com.example.sodv3203_carrentalapp.data.User
import com.example.sodv3203_carrentalapp.ui.theme.SODV3203_CarRentalAppTheme

@Composable
fun DisplayPageLogin(
    onSignUpButtonClicked: () -> Unit,
    onLoginButtonClicked: (isAuthenticated: Boolean, verifiedUser: User?) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AppViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    var username: String by remember{mutableStateOf("")}
    var password: String by remember{mutableStateOf("")}

    //Don't remove this line (It's updating the StateFlow of the viewmodel even though the rariable is not being used)
    val listAllUsers by viewModel.listAllUsers.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .fillMaxWidth()
            .padding(horizontal = 26.dp, vertical = 70.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Car Rental App", fontSize = 30.sp)

        Image(
            painter = painterResource(id = R.drawable.backgroundcarapp),
            contentDescription = "App Logo",
            modifier = Modifier.weight(1f)
        )

        val focusManager = LocalFocusManager.current
        OutlinedTextField(
            placeholder = { Text(text = "Username") },
            value = username,
            onValueChange = { username = it },
            label = { Text(text = "Username")},
            shape = RoundedCornerShape(20.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            ),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Person, contentDescription = "Username")
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
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
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Go
            ),
            keyboardActions = KeyboardActions(
                onGo = { loginUser(username, password, viewModel, onLoginButtonClicked) }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )
        
        Button(
            onClick = { loginUser(username, password, viewModel, onLoginButtonClicked) },
            colors = ButtonDefaults.buttonColors(),
            contentPadding = PaddingValues(start = 60.dp, end = 60.dp, top = 8.dp, bottom = 8.dp),
            modifier = Modifier
                .padding(top = 18.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Login", fontSize = 22.sp)
        }

        Button(
            onClick = {onSignUpButtonClicked()},
            colors = ButtonDefaults.buttonColors(),
            contentPadding = PaddingValues(start = 60.dp, end = 60.dp, top = 8.dp, bottom = 8.dp),
            modifier = Modifier
                .padding(top = 18.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Sign Up", fontSize = 22.sp)
        }

    }
}


fun loginUser(
    username: String,
    password: String,
    viewModel: AppViewModel,
    onLoginButtonClicked: (isAuthenticated: Boolean, verifiedUser: User?) -> Unit
) {
    var isAuthenticated: Boolean = false
    var verifiedUser: User? = viewModel.authenticate(username, password)
    if(verifiedUser!=null) {
        isAuthenticated = true
    }
    onLoginButtonClicked(isAuthenticated, verifiedUser)
}


@Preview(showBackground = true, heightDp = 800)
@Composable
fun DisplayPageLoginPreview() {
    SODV3203_CarRentalAppTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxWidth()
        ) {
            DisplayPageLogin(
                onSignUpButtonClicked = {},
                onLoginButtonClicked = {_, _ -> }
            )
        }
    }
}