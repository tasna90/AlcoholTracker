package com.example.alcoholTracker.presentation.registration

import android.content.SharedPreferences
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.alcoholTracker.R
import com.example.alcoholTracker.common.Constants
import com.example.alcoholTracker.data.User
import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(
    navController: NavController,
    sharedPreferences: SharedPreferences,
    registrationViewModel: RegistrationViewModel = hiltViewModel()
) {
    val drinks = registrationViewModel.drinksState.value.drinks

    var firstName: String by remember {
        mutableStateOf("")
    }
    var lastName: String by remember {
        mutableStateOf("")
    }
    var nickName: String by remember {
        mutableStateOf("")
    }
    var dateOfBirth: ZonedDateTime by remember {
        mutableStateOf(ZonedDateTime.now(ZoneId.systemDefault()))
    }
    var moneySpentOnAlcoholWeekly: Long by remember {
        mutableLongStateOf(0)
    }
    var consumedFavoriteDrinksWeekly: Long by remember {
        mutableLongStateOf(0)
    }
    var isDoBPickerOpen: Boolean by remember {
        mutableStateOf(false)
    }
    var datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = Instant.now().toEpochMilli()
    )
    var favoriteDrink: String by remember {
        mutableStateOf("")
    }
    var favoriteDrinkType: String by remember {
        mutableStateOf("")
    }
    var isFavoriteDrinkDropdownExpanded: Boolean by remember {
        mutableStateOf(false)
    }

    val verticalScrollState = rememberScrollState()

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(verticalScrollState)
    ) {
        Row(
            modifier = Modifier.padding(0.dp, 30.dp, 0.dp, 30.dp)
        ) {
            Text(
                text = "Registration",
                fontSize = 30.sp
            )
        }
        Row(
            modifier = Modifier.padding(0.dp, 30.dp, 0.dp, 30.dp)
        ) {
            Text(
                text = "Please provide some details about yourself."
            )
        }

        OutlinedTextField(
            modifier = Modifier.width(300.dp),
            value = firstName,
            label = { Text(text = "First name")},
            onValueChange = {
                firstName = it
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.id_card_24px),
                    contentDescription = ""
                )
            },
            singleLine = true
            //limit max width
        )
        OutlinedTextField(
            modifier = Modifier.width(300.dp),
            value = lastName,
            label = { Text(text = "Last name")},
            onValueChange = {
                lastName = it
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.id_card_24px),
                    contentDescription = ""
                )
            },
            singleLine = true
        )
        OutlinedTextField(
            modifier = Modifier.width(300.dp),
            value = nickName,
            label = { Text(text = "Nickname")},
            onValueChange = {
                nickName = it
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.id_card_24px),
                    contentDescription = ""
                )
            },
            singleLine = true
        )
        OutlinedTextField(
            readOnly = true,
            modifier = Modifier
                .clickable(
                    enabled = true,
                    onClick = { isDoBPickerOpen = true })
                .width(300.dp),
            value = dateOfBirth.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
            label = { Text(text = "Date of birth")},
            onValueChange = {
                dateOfBirth = ZonedDateTime.parse(it)
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.calendar_month_24px),
                    contentDescription = ""
                )
            }
        )
        if (isDoBPickerOpen) {
            DatePickerDialog(
                onDismissRequest = { isDoBPickerOpen = false },
                dismissButton = {
                    Button(
                        onClick = { isDoBPickerOpen = false }
                    ) {
                        Text(text = "Cancel")
                    }
                },
                confirmButton = {
                    Button(
                        onClick = {
                            isDoBPickerOpen = false
                            dateOfBirth = datePickerState.selectedDateMillis?.let {
                                Instant.ofEpochMilli(it).atZone(ZoneId.systemDefault())
                            }!!
                        }
                    ) {
                        Text(text = "Confirm")
                    }
                }
            ) {
                DatePicker(
                    state = datePickerState
                )
            }
        }

        OutlinedTextField(
            modifier = Modifier.width(300.dp),
            value = moneySpentOnAlcoholWeekly.toString(),
            label = { Text(text = "Money spent on alcohol weekly")},
            onValueChange = {
                if (it.isNotEmpty()) {
                    moneySpentOnAlcoholWeekly = it.toLong()
                }
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.money_24px),
                    contentDescription = ""
                )
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )

        ExposedDropdownMenuBox(
            expanded = isFavoriteDrinkDropdownExpanded,
            onExpandedChange = { isFavoriteDrinkDropdownExpanded = !isFavoriteDrinkDropdownExpanded }
        ) {
            OutlinedTextField(
                readOnly = true,
                value = favoriteDrink,
                label = { Text(text = "Favorite drink") },
                onValueChange = { },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.liquor_24px),
                        contentDescription = ""
                    )
                },
                singleLine = true,
                modifier = Modifier
                    .menuAnchor()
                    .width(300.dp)
            )

            ExposedDropdownMenu(
                expanded = isFavoriteDrinkDropdownExpanded,
                onDismissRequest = { isFavoriteDrinkDropdownExpanded = false }
            ) {
                drinks?.forEach { drink ->
                    DropdownMenuItem(
                        modifier = Modifier.width(300.dp),
                        text = { Text(text = drink.name) },
                        onClick = {
                            isFavoriteDrinkDropdownExpanded = false
                            favoriteDrink = drink.name
                            favoriteDrinkType = drink.type
                        }
                    )
                }

            }
        }

        OutlinedTextField(
            modifier = Modifier.width(300.dp).padding(bottom = 100.dp),
            value = consumedFavoriteDrinksWeekly.toString(),
            label = { Text(text = "Nr. of drinks per week") },
            onValueChange = {
                if (it.isNotEmpty()) {
                    consumedFavoriteDrinksWeekly = it.toLong()
                }
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.numbers_24px),
                    contentDescription = ""
                )
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )

        if (firstName.isNotEmpty() &&
            lastName.isNotEmpty() &&
            nickName.isNotEmpty() &&
            favoriteDrink.isNotEmpty() &&
            favoriteDrinkType.isNotEmpty() &&
            !moneySpentOnAlcoholWeekly.equals(0) &&
            !consumedFavoriteDrinksWeekly.equals(0)) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(0.dp, 30.dp, 0.dp, 30.dp)
            ) {
                Button(
                    onClick = { registrationViewModel.createUser(
                        User(
                            firstName = firstName,
                            lastName = lastName,
                            nickName = nickName,
                            dateOfBirth = ZonedDateTime.of(dateOfBirth.toLocalDate(), LocalTime.of(0, 0, 0), ZoneOffset.UTC),
                            dateOfAlcoholStop = ZonedDateTime.of(LocalDate.of(2023, 12, 12), LocalTime.of(0,0,0), ZoneOffset.UTC), // <- in NOT IN RELEASE APP
                            favoriteDrink = favoriteDrink,
                            favoriteDrinkType = favoriteDrinkType,
                            moneySpentOnAlcoholWeekly = moneySpentOnAlcoholWeekly,
                            consumedFavoriteDrinksWeekly = consumedFavoriteDrinksWeekly
                        ))
                        val sharedPreferencesEditor = sharedPreferences.edit()
                        sharedPreferencesEditor.clear()
                        sharedPreferencesEditor.putBoolean(Constants.SHOULD_REGISTER, false)
                        sharedPreferencesEditor.apply()
                        navController.navigate("home_screen")
                    }
                ) {
                    Text(text = "Continue")
                }
            }
        }
    }
}