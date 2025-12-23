package com.example.alcoholTracker.presentation.home.components.home_community_card

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HomeCommunityCard(
    navController: NavController
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
            //navController.navigate(NavigationConstants.COMMUNITY_SCREEN)
        }
    ) {
        HomeCommunityCardHeader()
    }
}