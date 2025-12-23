package com.example.alcoholTracker.presentation.home.components.home_time_passed_card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.alcoholTracker.presentation.home.PassedTimeState

@Composable
fun HomeTimePassedCardCounter(
    passedTimeState: PassedTimeState
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, bottom = 10.dp, top = 100.dp)
    ) {
        TimePassedCardDays(passedTimeState)
        TimePassedCardHours(passedTimeState)
        TimePassedCardMinutes(passedTimeState)
        TimePassedCardSeconds(passedTimeState)
    }
}