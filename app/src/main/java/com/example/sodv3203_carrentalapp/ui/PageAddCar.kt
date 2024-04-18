package com.example.sodv3203_carrentalapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sodv3203_carrentalapp.R
import com.example.sodv3203_carrentalapp.data.Car
import com.example.sodv3203_carrentalapp.ui.theme.SODV3203_CarRentalAppTheme

@Composable
fun DisplayPageAddCar(
    modifier: Modifier = Modifier,
    onAddNewCarButtonClicked: (newCar: Car) -> Unit,
    onCancelButtonClicked: () -> Unit
) {

    var imageResourceId by remember { mutableIntStateOf(R.drawable.car00) }
    var name by remember { mutableStateOf("") }
    var feature by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var seat by remember { mutableIntStateOf(4) }
    var bags by remember { mutableIntStateOf(0) }
    var doors by remember { mutableIntStateOf(4) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .fillMaxWidth()
            .padding(horizontal = 26.dp, vertical = 70.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Add Car", fontSize = 30.sp)
        Spacer(modifier = Modifier.padding(bottom = 20.dp))

        val focusManager = LocalFocusManager.current

        OutlinedTextField(
            placeholder = { Text(text = "Name") },
            value = name,
            onValueChange = { name = it },
            label = { Text(text = "Name")},
            shape = RoundedCornerShape(20.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            ),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.icon_car),
                    contentDescription = "",
                    modifier =  Modifier.size(30.dp)
                )
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
            placeholder = { Text(text = "Feature") },
            value = feature,
            onValueChange = { feature = it },
            label = { Text(text = "Feature")},
            shape = RoundedCornerShape(20.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            ),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.icon_features),
                    contentDescription = "",
                    modifier =  Modifier.size(30.dp)
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            )
        )

        OutlinedTextField(
            placeholder = { Text(text = "Category") },
            value = category,
            onValueChange = { category = it },
            label = { Text(text = "Category")},
            shape = RoundedCornerShape(20.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            ),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.icon_category),
                    contentDescription = "",
                    modifier =  Modifier.size(30.dp)
                )
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
            placeholder = { Text(text = "Seats") },
            value = seat.toString(),
            onValueChange = {
                seat = try {
                    it.toInt()
                } catch(e: Exception) {
                    0
                }
            },
            label = { Text(text = "Seats") },
            shape = RoundedCornerShape(20.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            ),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.icon_seats),
                    contentDescription = "",
                    modifier =  Modifier.size(30.dp)
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            )
        )

        OutlinedTextField(
            placeholder = { Text(text = "Bags") },
            value = bags.toString(),
            onValueChange = {
                validInput:String ->
                bags = validInput.filter { it.isDigit() }.toInt()
            },
            label = { Text(text = "Bags") },
            shape = RoundedCornerShape(20.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            ),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.icon_bags),
                    contentDescription = "",
                    modifier =  Modifier.size(30.dp)
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            )
        )

        OutlinedTextField(
            placeholder = { Text(text = "Doors") },
            value = doors.toString(),
            onValueChange = {
                validInput:String ->
                doors = validInput.filter { it.isDigit() }.toInt()
            },
            label = { Text(text = "Doors") },
            shape = RoundedCornerShape(20.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            ),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.icon_doors),
                    contentDescription = "",
                    modifier =  Modifier.size(30.dp)
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { addCar(
                    imageResourceId = imageResourceId,
                    name = name,
                    feature = feature,
                    category = category,
                    seat = seat,
                    bags = bags,
                    doors = doors,
                    onAddNewCarButtonClicked = onAddNewCarButtonClicked
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
                    onClick = { addCar(
                        imageResourceId = imageResourceId,
                        name = name,
                        feature = feature,
                        category = category,
                        seat = seat,
                        bags = bags,
                        doors = doors,
                        onAddNewCarButtonClicked = onAddNewCarButtonClicked
                    ) }
                ) {
                    Text(stringResource(R.string.button_add_car), fontSize = 22.sp)
                }

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { onCancelButtonClicked() }
                ) {
                    Text(stringResource(R.string.button_cancel), fontSize = 22.sp)
                }
            }
        }
    }
}

fun addCar(
    imageResourceId: Int,
    name: String,
    feature: String,
    category: String,
    seat: Int,
    bags: Int,
    doors: Int,
    onAddNewCarButtonClicked: (Car) -> Unit
) {
    val newCar = Car(
        imageResourceId = imageResourceId,
        name = name,
        feature = feature,
        category = category,
        seat = seat,
        bags = bags,
        doors = doors
    )
    onAddNewCarButtonClicked(newCar)
}


@Preview(showBackground = true, heightDp = 800)
@Composable
fun DisplayPageAddCarPreview() {
    SODV3203_CarRentalAppTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxWidth()
        ) {
            DisplayPageAddCar(
                onAddNewCarButtonClicked = {},
                onCancelButtonClicked = {}
            )
        }
    }
}