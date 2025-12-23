package com.example.alcoholTracker.presentation.home.components.home_health_improvements_card

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.alcoholTracker.presentation.common.NavigationConstants
import com.example.alcoholTracker.presentation.common.ui.components.CommonLoading
import com.example.alcoholTracker.presentation.home.HomeViewModel
import com.example.alcoholTracker.presentation.home.PassedTimeState

@Composable
fun HomeHealthImprovementsCard(
    passedTimeState: PassedTimeState,
    navController: NavController,
    homeViewModel: HomeViewModel
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .size(
                width = 390.dp, height = 250.dp
            )
            .padding(
                bottom = 10.dp
            ),
        onClick = {
            if (passedTimeState.days != null) {
                navController.navigate(
                    NavigationConstants.HEALTH_IMPROVEMENTS_SCREEN + "/${passedTimeState.days}"
                )
            }
        }
    ) {
        HomeHealthImprovementsCardHeader()

        if (homeViewModel.healthImprovementsState.value.isSuccess && passedTimeState.days != null) {
            HomeHealthImprovementsAnimationAndDescription(
                homeViewModel,
                passedTimeState
            )
        }

        if (homeViewModel.healthImprovementsState.value.isLoading) {
            CommonLoading()
        }
    }
}