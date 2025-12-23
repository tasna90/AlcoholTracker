package com.example.alcoholTracker.presentation.home.components.home_milestones_card

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.alcoholTracker.presentation.common.NavigationConstants
import com.example.alcoholTracker.presentation.common.ui.components.CommonLoading
import com.example.alcoholTracker.presentation.home.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeMilestonesCard(
    navController: NavController,
    homeViewModel: HomeViewModel
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .size(
                width = 390.dp, height = 240.dp
            )
            .padding(
                bottom = 10.dp
            ),
        onClick = {
            navController.navigate(
                NavigationConstants.MILESTONES_SCREEN
            )
        }
    ) {
        HomeMilestonesCardHeader()

        if (homeViewModel.milestonesState.value.isSuccess) {
            HomeMilestonesCardImageAndName(
                homeViewModel
            )
        }

        if (homeViewModel.milestonesState.value.isLoading) {
            CommonLoading()
        }
    }
}