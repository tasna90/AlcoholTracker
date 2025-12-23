package com.example.alcoholTracker.presentation.home.components.home_health_improvements_card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.alcoholTracker.R
import com.example.alcoholTracker.presentation.home.HomeViewModel
import com.example.alcoholTracker.presentation.home.PassedTimeState

@Composable
fun HomeHealthImprovementsAnimationAndDescription(
    homeViewModel: HomeViewModel,
    passedTimeState: PassedTimeState
) {
    val checkListComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.checklist)
    )

    var isCheckListPlaying by remember {
        mutableStateOf(true)
    }

    val checkListProgress by animateLottieCompositionAsState(
        composition = checkListComposition,
        iterations = LottieConstants.IterateForever,
        isPlaying = isCheckListPlaying
    )

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.Bottom,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
    ) {

        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .height(200.dp)
                .weight(2f)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .height(200.dp)
                    .weight(1f)
            ) {
                LottieAnimation(
                    modifier = Modifier.size(100.dp),
                    composition = checkListComposition,
                    progress = { checkListProgress }
                )
            }

            HomeHealthImprovementsCardDescription(
                homeViewModel,
                passedTimeState
            )
        }
    }
}