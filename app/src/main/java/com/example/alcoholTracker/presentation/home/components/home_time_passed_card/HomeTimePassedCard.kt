package com.example.alcoholTracker.presentation.home.components.home_time_passed_card

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.alcoholTracker.presentation.common.ui.components.CommonLoading
import com.example.alcoholTracker.presentation.home.HomeState
import com.example.alcoholTracker.presentation.home.PassedTimeState

@Composable
fun HomeTimePassedCard(
    state: HomeState,
    passedTimeState: PassedTimeState,
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .size(
                width = 390.dp, height = 200.dp
            )
            .padding(
                bottom = 10.dp
            ),
    ) {
        /*Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "IDE EGY NAGY ÓRA MEG HÁTTÉR",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(start = 10.dp)
            )
        }*/

        if (state.isSuccess) {
            HomeTimePassedCardCounter(
                passedTimeState
            )
        }

        if (state.isLoading) {
            CommonLoading()
        }

        if (state.isError) {
            Toast.makeText(
                LocalContext.current,
                state.message.toString(),
                Toast.LENGTH_LONG
            ).show()
        }
    }
}