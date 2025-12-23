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
import com.example.alcoholTracker.presentation.home.PassedTimeState

@Composable
fun HomeStatisticsCardSoberDaysAnimationAndText(
    calendarComposition: LottieComposition?,
    calendarProgress: Float,
    passedTimeState: PassedTimeState
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LottieAnimation(
            modifier = Modifier.size(100.dp),
            composition = calendarComposition,
            progress = { calendarProgress }
        )
        Text(text = passedTimeState.days.toString())
        Text(text = "sober days", minLines = 2)
    }
}