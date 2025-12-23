package com.example.alcoholTracker.presentation.home.components.home_statistics_card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.alcoholTracker.R
import com.example.alcoholTracker.presentation.home.MoneyGainedState
import com.example.alcoholTracker.presentation.home.PassedTimeState
import com.example.alcoholTracker.presentation.home.SkippedDrinksState

@Composable
fun HomeStatisticsCardAnimations(
    passedTimeState: PassedTimeState,
    skippedDrinkState: SkippedDrinksState,
    moneyGainedState: MoneyGainedState
) {
    val calendarComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.calendar)
    )

    var isCalendarPlaying by remember {
        mutableStateOf(true)
    }

    val calendarProgress by animateLottieCompositionAsState(
        composition = calendarComposition,
        iterations = LottieConstants.IterateForever,
        isPlaying = isCalendarPlaying
    )

    val drinkComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.beer)
    )

    var isDrinkPlaying by remember {
        mutableStateOf(true)
    }

    val drinkProgress by animateLottieCompositionAsState(
        composition = drinkComposition,
        iterations = LottieConstants.IterateForever,
        isPlaying = isDrinkPlaying
    )

    val moneyRainComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.money_rain)
    )

    var isMoneyRainPlaying by remember {
        mutableStateOf(true)
    }

    val moneyRainProgress by animateLottieCompositionAsState(
        composition = moneyRainComposition,
        iterations = LottieConstants.IterateForever,
        isPlaying = isMoneyRainPlaying
    )

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.Bottom,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
    ) {
        HomeStatisticsCardSoberDaysAnimationAndText(
            calendarComposition,
            calendarProgress,
            passedTimeState
        )
        HomeStatisticsMissedDrinksAnimationAndText(
            drinkComposition,
            drinkProgress,
            skippedDrinkState
        )
        HomeStatisticsCardMoneyGainedAnimationAndText(
            moneyRainComposition,
            moneyRainProgress,
            moneyGainedState)
    }
}