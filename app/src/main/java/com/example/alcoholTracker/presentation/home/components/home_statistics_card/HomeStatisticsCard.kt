package com.example.alcoholTracker.presentation.home.components.home_statistics_card

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.alcoholTracker.presentation.common.NavigationConstants
import com.example.alcoholTracker.presentation.common.ui.components.CommonLoading
import com.example.alcoholTracker.presentation.home.HomeState
import com.example.alcoholTracker.presentation.home.MoneyGainedState
import com.example.alcoholTracker.presentation.home.PassedTimeState
import com.example.alcoholTracker.presentation.home.SkippedDrinksState

@Composable
fun HomeStatisticsCard(
    state: HomeState,
    navController: NavController,
    passedTimeState: PassedTimeState,
    skippedDrinkState: SkippedDrinksState,
    moneyGainedState: MoneyGainedState,
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .size(
                width = 390.dp, height = 200.dp
            )
            .padding(
                bottom = 10.dp
            ),
        onClick = {
            navController.navigate(
                NavigationConstants.STATISTICS_SCREEN
            )
        }
    ) {
        HomeStatisticsCardHeader()

        if (state.isSuccess) {
            HomeStatisticsCardAnimations(
                passedTimeState,
                skippedDrinkState,
                moneyGainedState
            )
        }

        if (state.isLoading) {
            CommonLoading()
        }

        if (state.isError) {
            Toast.makeText(
                LocalContext.current,
                state.message.toString(),
                Toast.LENGTH_LONG
            ).show()
        }
    }
}

