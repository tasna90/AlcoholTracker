package com.example.alcoholTracker.presentation.home.components.home_statistics_card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.compose.LottieAnimation
import com.example.alcoholTracker.presentation.home.SkippedDrinksState

@Composable
fun HomeStatisticsMissedDrinksAnimationAndText(
    drinkComposition: LottieComposition?,
    drinkProgress: Float,
    skippedDrinkState: SkippedDrinksState
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(start = 10.dp, end = 10.dp)
    ) {
        LottieAnimation(
            modifier = Modifier.size(100.dp),
            composition = drinkComposition,
            progress = { drinkProgress }
        )
        Text(text = skippedDrinkState.numberOfSkippedDrinks.toString())
        Text(text = "missed drinks", minLines = 2)
    }
}