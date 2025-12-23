package com.example.alcoholTracker.presentation.home.components.home_statistics_card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.compose.LottieAnimation
import com.example.alcoholTracker.presentation.home.MoneyGainedState

@Composable
fun HomeStatisticsCardMoneyGainedAnimationAndText(
    moneyRainComposition: LottieComposition?,
    moneyRainProgress: Float,
    moneyGainedState: MoneyGainedState
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LottieAnimation(
            modifier = Modifier.size(100.dp),
            composition = moneyRainComposition,
            progress = { moneyRainProgress }
        )
        Text(text = moneyGainedState.moneyGained.toString()) // preferred drink should be asked to calculate a value of missed drinks, icon should change according to this
        Text(text = "money gained", minLines = 2)
    }
}