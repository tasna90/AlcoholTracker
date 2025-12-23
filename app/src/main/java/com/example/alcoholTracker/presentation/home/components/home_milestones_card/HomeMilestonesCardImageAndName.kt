package com.example.alcoholTracker.presentation.home.components.home_milestones_card

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.carousel.HorizontalUncontainedCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.alcoholTracker.presentation.home.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeMilestonesCardImageAndName(
    homeViewModel: HomeViewModel
) {
    val milestones = homeViewModel.milestonesState.value.milestones

    HorizontalUncontainedCarousel(
        state = rememberCarouselState { milestones!!.count() },
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 16.dp, bottom = 16.dp),
        itemWidth = 140.dp,
        itemSpacing = 8.dp,
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) { i ->
        val milestone = milestones?.get(i)
        Column {
            Image(
                modifier = Modifier
                    .size(150.dp)
                    .maskClip(MaterialTheme.shapes.small),
                contentDescription = milestone?.description,
                painter = painterResource(HomeMilestonesCardHelper.getIconForMilestone(milestone)),
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center
            )

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = milestone!!.name
                )
            }
        }
    }
}