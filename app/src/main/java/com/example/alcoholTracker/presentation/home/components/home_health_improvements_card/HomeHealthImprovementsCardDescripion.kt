package com.example.alcoholTracker.presentation.home.components.home_health_improvements_card

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.alcoholTracker.data.HealthImprovement
import com.example.alcoholTracker.presentation.home.HomeViewModel
import com.example.alcoholTracker.presentation.home.PassedTimeState

@Composable
fun HomeHealthImprovementsCardDescription(
    homeViewModel: HomeViewModel,
    passedTimeState: PassedTimeState
) {
    val verticalScrollStateForHealthImprovementDescription = rememberScrollState()

    var latestHealthImprovement = HealthImprovement(
        uId = 0,
        name = "Hold on",
        description = "Keep up the good work, the results are coming!",
        happensAfterGivenDays = 0
    )

    homeViewModel.healthImprovementsState.value.healthImprovements?.onEach { healthImprovement ->
        passedTimeState.days.let { days ->
            healthImprovement.happensAfterGivenDays.let { happensAfterGivenDays ->
                if (days.toInt() >= happensAfterGivenDays) {
                    latestHealthImprovement = healthImprovement
                }
            }
        }
    }

    Text(
        text = latestHealthImprovement.name,
        modifier = Modifier.padding(bottom = 10.dp)
    )

    Text(
        text = latestHealthImprovement.description,
        modifier = Modifier.verticalScroll(
            state = verticalScrollStateForHealthImprovementDescription,
            enabled = true
        )
    )
}