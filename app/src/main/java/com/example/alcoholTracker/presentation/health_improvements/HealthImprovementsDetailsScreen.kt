package com.example.alcoholTracker.presentation.health_improvements

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.alcoholTracker.R

@Composable
fun HealthImprovementsDetailsScreen(
    navController: NavController,
    healthImprovementsDetailsViewModel: HealthImprovementsDetailsViewModel = hiltViewModel()
) {
    val state = healthImprovementsDetailsViewModel.state.value

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

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp, 10.dp, 10.dp, 0.dp)
    ) {
        if (state.isLoading) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxSize()
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

        if (state.isSuccess) {
            state.daysPassed?.let { days ->
                state.healthImprovement.let { healthImprovement ->
                    healthImprovement?.happensAfterGivenDays?.toFloat()?.let { happensAfterGivenDays ->
                        days.div(happensAfterGivenDays).let { result ->
                            val percentage = if (result in 0.0..0.9) {
                                result
                            } else {
                                1f
                            }

                            HealthImprovementDetailsCard(
                                progress = percentage,
                                name = healthImprovement.name,
                                description = healthImprovement.description
                            )
                        }
                    }
                }
            }
        }
    }
}

@SuppressLint("DefaultLocale")
@Composable
fun HealthImprovementDetailsCard(
    progress: Float,
    name: String,
    description: String
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .size(
                width = 390.dp, height = 300.dp
            )
            .padding(
                bottom = 20.dp
            )
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
        ) {
            Text(text = name)
        }

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(3f)
            ) {
                LinearProgressIndicator(
                    progress = { progress },
                    color = MaterialTheme.colorScheme.tertiary,
                    strokeCap = StrokeCap.Round,
                    modifier = Modifier.height(10.dp)
                )
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {
                Text(text = String.format("%.0f", progress.times(100)) + " %")
            }
        }

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp ,bottom = 20.dp)
        ) {
            Text(text = description)
        }
    }
}