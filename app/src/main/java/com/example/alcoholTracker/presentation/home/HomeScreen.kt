package com.example.alcoholTracker.presentation.home

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.carousel.HorizontalUncontainedCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.alcoholTracker.R
import com.example.alcoholTracker.data.HealthImprovement
import com.example.alcoholTracker.data.Milestone

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val state = homeViewModel.state.value
    val passedTimeState = homeViewModel.passedTimeState.value
    val moneyGainedState = homeViewModel.moneyGainedState.value
    val skippedDrinkState = homeViewModel.skippedDrinksState.value

    val verticalScrollState = rememberScrollState()

    val verticalScrollStateForHealthImprovementDescription = rememberScrollState()

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

    val beerComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.beer)
    )

    var isBeerPlaying by remember {
        mutableStateOf(true)
    }

    val beerProgress by animateLottieCompositionAsState(
        composition = beerComposition,
        iterations = LottieConstants.IterateForever,
        isPlaying = isBeerPlaying
    )

    val wineComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.beer)
    )
    
    var isWinePlaying by remember {
        mutableStateOf(true)
    }

    val wineProgress by animateLottieCompositionAsState(
        composition = wineComposition,
        iterations = LottieConstants.IterateForever,
        isPlaying = isWinePlaying
    )

    val whiskeyComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.beer)
    )
    
    var isWhiskeyPlaying by remember {
        mutableStateOf(true)
    }

    val whiskeyProgress by animateLottieCompositionAsState(
        composition = whiskeyComposition,
        iterations = LottieConstants.IterateForever,
        isPlaying = isWhiskeyPlaying
    )

    val spiritComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.beer)
    )

    var isSpiritPlaying by remember {
        mutableStateOf(true)
    }

    val spiritProgress by animateLottieCompositionAsState(
        composition = spiritComposition,
        iterations = LottieConstants.IterateForever,
        isPlaying = isSpiritPlaying
    )

    val liqueurComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.beer)
    )

    var isLiqueurPlaying by remember {
        mutableStateOf(true)
    }

    val liqueurProgress by animateLottieCompositionAsState(
        composition = liqueurComposition,
        iterations = LottieConstants.IterateForever,
        isPlaying = isLiqueurPlaying
    )

    val cocktailComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.beer)
    )

    var isCocktailPlaying by remember {
        mutableStateOf(true)
    }

    val cocktailProgress by animateLottieCompositionAsState(
        composition = cocktailComposition,
        iterations = LottieConstants.IterateForever,
        isPlaying = isCocktailPlaying
    )

    val calendarComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.calendar)
    )

    var isCalendarPlaying by remember {
        mutableStateOf(true)
    }

    val calendarProgress by animateLottieCompositionAsState(
        composition = calendarComposition,
        iterations = LottieConstants.IterateForever,
        isPlaying = isCalendarPlaying
    )

    val moneyRainComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.money_rain)
    )

    var isMoneyRainPlaying by remember {
        mutableStateOf(true)
    }

    val moneyRainProgress by animateLottieCompositionAsState(
        composition = moneyRainComposition,
        iterations = LottieConstants.IterateForever,
        isPlaying = isMoneyRainPlaying
    )

    val heartBeatComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.money_rain)
    )

    var isHeartBeatPlaying by remember {
        mutableStateOf(true)
    }

    val heartBeatProgress by animateLottieCompositionAsState(
        composition = heartBeatComposition,
        iterations = LottieConstants.IterateForever,
        isPlaying = isHeartBeatPlaying
    )

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



    if (state.user != null) {


        //daysWithoutAlcohol = Instant.now().minusMillis(state.user.dateOfAlcoholStop .toLong()).toEpochMilli().days



        /*val moneyGainedByNotDrinking = state.user.moneySpentOnAlcoholWeekly?.let {
            daysWithoutAlcohol?.inWholeDays?.times(it.toLong())
        }*/
    }

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp, 10.dp, 10.dp, 0.dp)
            .verticalScroll(
                state = verticalScrollState
            )
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
                    text = "IDE EGY NAGY ÓRA MEG BASZÓÓÓÓ HÁTTÉR",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(start = 10.dp)
                )
            }*/

            if (state.isLoading) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    LottieAnimation(
                        modifier = Modifier.size(200.dp),
                        composition = composition,
                        progress = { progress }
                    )
                }
            } else {
                LaunchedEffect(Unit) {
                    isPlaying = false
                }
            }

            if (state.isSuccess) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp, bottom = 10.dp, top = 100.dp)
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
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.size(70.dp)
                    ) {
                        Text(
                            text = passedTimeState.hours.toString(),
                            fontWeight = FontWeight.Bold,
                            fontSize = 30.sp
                        )
                        Text(text = "hours")
                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.size(70.dp)
                    ) {
                        Text(
                            text = passedTimeState.minutes.toString(),
                            fontWeight = FontWeight.Bold,
                            fontSize = 30.sp
                        )
                        Text(text = "minutes")
                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.size(70.dp)
                    ) {
                        Text(
                            text = passedTimeState.seconds.toString(),
                            fontWeight = FontWeight.Bold,
                            fontSize = 30.sp
                        )
                        Text(text = "seconds")
                    }

                }
            }
        }

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
            onClick = {
                navController.navigate("statistics_screen")
            }
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Statistics",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(start = 10.dp)
                )
            }

            if (state.isError) {
                /*LaunchedEffect(snackbarHostState) {
                    snackbarHostState.showSnackbar(
                        message = state.message.toString(),
                        duration = SnackbarDuration.Long
                    )
                }*/
                Toast.makeText(LocalContext.current, state.message.toString(), Toast.LENGTH_LONG).show()
            }

            if (state.isLoading) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    LottieAnimation(
                        modifier = Modifier.size(200.dp),
                        composition = composition,
                        progress = { progress }
                    )
                }
            } else {
                LaunchedEffect(Unit) {
                    isPlaying = false
                }
            }



            if (state.isSuccess) {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.Bottom,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
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
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(start = 10.dp, end = 10.dp)
                    ) {
                        when (state.user?.favoriteDrinkType) {
                            "beer" -> {
                                CreateFavoriteDrinkAnimation(
                                    beerComposition,
                                    beerProgress,
                                    skippedDrinkState
                                )
                            }
                            "wine" -> {
                                CreateFavoriteDrinkAnimation(
                                    wineComposition,
                                    wineProgress,
                                    skippedDrinkState
                                )
                            }
                            "whiskey" -> {
                                CreateFavoriteDrinkAnimation(
                                    beerComposition,
                                    whiskeyProgress,
                                    skippedDrinkState
                                )
                            }
                            "spirit" -> {
                                CreateFavoriteDrinkAnimation(
                                    beerComposition,
                                    spiritProgress,
                                    skippedDrinkState
                                )
                            }
                            "liqueur" -> {
                                CreateFavoriteDrinkAnimation(
                                    beerComposition,
                                    liqueurProgress,
                                    skippedDrinkState
                                )
                            }
                            "cocktail" -> {
                                CreateFavoriteDrinkAnimation(
                                    beerComposition,
                                    cocktailProgress,
                                    skippedDrinkState
                                )
                            }
                        }
                    }
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
            }
        }

        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ),
            modifier = Modifier
                .size(
                    width = 390.dp, height = 250.dp
                )
                .padding(
                    bottom = 10.dp
                ),
            onClick = {
                if (passedTimeState.days != null) {
                    navController.navigate("health_improvements_screen/${passedTimeState.days}")
                }
            }
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Health improvements",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(start = 10.dp)
                )
            }

            if (homeViewModel.healthImprovementsState.value.isLoading) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    LottieAnimation(
                        modifier = Modifier.size(200.dp),
                        composition = composition,
                        progress = { progress }
                    )
                }
            } else {
                LaunchedEffect(Unit) {
                    isPlaying = false
                }
            }

            if (homeViewModel.healthImprovementsState.value.isSuccess && passedTimeState.days != null) {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.Bottom,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
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
                    Column(
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .height(200.dp)
                            .weight(2f)
                    ) {
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
                }
            }
        }

        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ),
            modifier = Modifier
                .size(
                    width = 390.dp, height = 240.dp
                )
                .padding(
                    bottom = 10.dp
                ),
            onClick = {
                navController.navigate("milestones_screen")
            }
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Milestones",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(start = 10.dp)
                )
            }

            if (homeViewModel.milestonesState.value.isLoading) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    LottieAnimation(
                        modifier = Modifier.size(200.dp),
                        composition = composition,
                        progress = { progress }
                    )
                }
            } else {
                LaunchedEffect(Unit) {
                    isPlaying = false
                }
            }

            if (homeViewModel.milestonesState.value.isSuccess) {
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
                            painter = painterResource(getIconForMilestone(milestone)),
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
        }

        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ),
            modifier = Modifier
                .size(
                    width = 390.dp, height = 240.dp
                )
                .padding(
                    bottom = 10.dp
                ),
            onClick = {
                //navController.navigate("milestones_screen")
            }
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Community",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(start = 10.dp)
                )
            }
        }

        //GOALS
    }
}

private fun getIconForMilestone(milestone: Milestone?): Int {
    when(milestone?.icon) {
        "money_24px" -> {
            return R.drawable.money_24px
        }
        "liquor_24px" -> {
            return R.drawable.liquor_24px
        }
        "mail_24px" -> {
            return R.drawable.mail_24px
        }
    }
    return R.drawable.numbers_24px
}

@Composable
fun CreateFavoriteDrinkAnimation(
    favoriteDrinkComposition: LottieComposition?,
    favoriteDrinkProgress: Float,
    skippedDrinkState: SkippedDrinksState
) {
    LottieAnimation(
        modifier = Modifier.size(100.dp),
        composition = favoriteDrinkComposition,
        progress = { favoriteDrinkProgress }
    )
    Text(text = skippedDrinkState.numberOfSkippedDrinks.toString())
    Text(text = "missed drinks", minLines = 2)
}