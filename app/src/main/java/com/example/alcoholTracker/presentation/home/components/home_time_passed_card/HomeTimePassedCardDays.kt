package com.example.alcoholTracker.presentation.home.components.home_time_passed_card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alcoholTracker.presentation.home.PassedTimeState

@Composable
fun TimePassedCardDays(
    passedTimeState: PassedTimeState
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.size(70.dp)
    ) {
        Text(
            text = passedTimeState.days.toString(),
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp
        )
        Text(text= "days")
    }
}