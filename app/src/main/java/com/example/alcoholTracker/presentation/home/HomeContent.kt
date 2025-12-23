package com.example.alcoholTracker.presentation.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.alcoholTracker.presentation.home.components.home_community_card.HomeCommunityCard
import com.example.alcoholTracker.presentation.home.components.home_health_improvements_card.HomeHealthImprovementsCard
import com.example.alcoholTracker.presentation.home.components.home_milestones_card.HomeMilestonesCard
import com.example.alcoholTracker.presentation.home.components.home_statistics_card.HomeStatisticsCard
import com.example.alcoholTracker.presentation.home.components.home_time_passed_card.HomeTimePassedCard

@Composable
fun HomeContent(
    state: HomeState,
    navController: NavController,
    passedTimeState: PassedTimeState,
    skippedDrinkState: SkippedDrinksState,
    moneyGainedState: MoneyGainedState,
    homeViewModel: HomeViewModel
) {
    HomeTimePassedCard(
        state,
        passedTimeState
    )

    HomeStatisticsCard(
        state,
        navController,
        passedTimeState,
        skippedDrinkState,
        moneyGainedState
    )

    HomeHealthImprovementsCard(
        passedTimeState,
        navController,
        homeViewModel
    )

    HomeMilestonesCard(
        navController,
        homeViewModel
    )

    HomeCommunityCard(
        navController
    )
}