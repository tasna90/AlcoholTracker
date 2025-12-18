package com.example.alcoholTracker.presentation.statistics

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.alcoholTracker.R

@Composable
fun StatisticsScreen(
    navController: NavController,
    statisticsViewModel: StatisticsViewModel = hiltViewModel()
) {
    val statisticsState = statisticsViewModel.statisticsState.value

    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.loading)
    )

    var isPlaying by remember {
        mutableStateOf(true)
    }

    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever,
        isPlaying = isPlaying
    )

    if (statisticsState.isLoading) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                LottieAnimation(
                    modifier = Modifier.size(200.dp),
                    composition = composition,
                    progress = { progress }
                )
            }
        }
    } else {
        LaunchedEffect(Unit) {
            isPlaying = false
        }
    }

    if (statisticsState.isSuccess) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 10.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "Avoided drinks",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 30.dp)
                )
            }

            CreateRowForAvoidedDrinksStatistics(
                period = "Daily",
                avoidedDrinksPeriod = statisticsState.avoidedDrinks?.daily.toString()
            )
            CreateRowForAvoidedDrinksStatistics(
                period = "Weekly",
                avoidedDrinksPeriod = statisticsState.avoidedDrinks?.weekly.toString()
            )
            CreateRowForAvoidedDrinksStatistics(
                period = "Monthly",
                avoidedDrinksPeriod = statisticsState.avoidedDrinks?.monthly.toString()
            )
            CreateRowForAvoidedDrinksStatistics(
                period = "Yearly",
                avoidedDrinksPeriod = statisticsState.avoidedDrinks?.yearly.toString()
            )


            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "Saved money",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 30.dp)
                )
            }


            CreateRowForSavedMoneyStatistics(
                period = "Daily",
                savedMoneyPeriod = statisticsState.savedMoney?.daily.toString()
            )
            CreateRowForSavedMoneyStatistics(
                period = "Weekly",
                savedMoneyPeriod = statisticsState.savedMoney?.weekly.toString()
            )
            CreateRowForSavedMoneyStatistics(
                period = "Monthly",
                savedMoneyPeriod = statisticsState.savedMoney?.monthly.toString()
            )
            CreateRowForSavedMoneyStatistics(
                period = "Yearly",
                savedMoneyPeriod = statisticsState.savedMoney?.yearly.toString()
            )
        }
    }
}

@Composable
fun CreateRowForAvoidedDrinksStatistics(
    period: String,
    avoidedDrinksPeriod: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 30.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(2f)
        ) {
            Text(
                text = period
            )
        }

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(2f)
        ) {
            Text(
                text = avoidedDrinksPeriod
            )
        }
    }
}

@Composable
fun CreateRowForSavedMoneyStatistics(
    period: String,
    savedMoneyPeriod: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 30.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(2f)
        ) {
            Text(
                text = period
            )
        }

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(2f)
        ) {
            Text(
                text = savedMoneyPeriod
            )
        }
    }
}