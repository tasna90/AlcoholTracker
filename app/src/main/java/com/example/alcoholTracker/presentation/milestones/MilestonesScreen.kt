package com.example.alcoholTracker.presentation.milestones

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MilestonesScreen (
    navController: NavController,
    milestonesViewModel: MilestonesViewModel = hiltViewModel()
) {
    val state = milestonesViewModel.state




}