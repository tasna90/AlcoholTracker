package com.example.alcoholTracker

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.alcoholTracker.presentation.common.BaseViewModel
import com.example.alcoholTracker.common.Constants
import com.example.alcoholTracker.presentation.common.AppScaffoldState
import com.example.alcoholTracker.presentation.health_improvements.HealthImprovementsDetailsScreen
import com.example.alcoholTracker.presentation.health_improvements.HealthImprovementsScreen
import com.example.alcoholTracker.presentation.home.HomeScreen
import com.example.alcoholTracker.presentation.milestones.MilestonesScreen
import com.example.alcoholTracker.presentation.profile.ProfileScreen
import com.example.alcoholTracker.presentation.registration.RegistrationScreen
import com.example.alcoholTracker.presentation.settings.SettingsScreen
import com.example.alcoholTracker.presentation.statistics.StatisticsScreen
import com.example.alcoholTracker.ui.theme.AlcoholTrackerTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    private val baseViewModel: BaseViewModel by viewModels()

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val isDarkMode by baseViewModel.isDarkThemeApplied.collectAsStateWithLifecycle()

            AlcoholTracker(isDarkMode = isDarkMode)
        }
    }

    @SuppressLint("StateFlowValueCalledInComposition")
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun AlcoholTracker(isDarkMode: Boolean) {
        AlcoholTrackerTheme(isDarkMode = isDarkMode) {
            val navController = rememberNavController()

            var startDestinationRoute: String = "home_screen"

            var appScaffoldState by remember {
                mutableStateOf(
                    AppScaffoldState("", "")
                )
            }

            if (::sharedPreferences.isInitialized && sharedPreferences.getBoolean(Constants.SHOULD_REGISTER, true)) {

                if (sharedPreferences.getBoolean(Constants.SHOULD_CREATE_HEALTH_IMPROVEMENTS, true)) {
                    runBlocking {
                        launch {
                            val healthImprovementsResult = baseViewModel.createHealthImprovements()
                            println(healthImprovementsResult)
                            val sharedPreferencesEditor = sharedPreferences.edit()
                            sharedPreferencesEditor.clear()
                            sharedPreferencesEditor.putBoolean(Constants.SHOULD_CREATE_HEALTH_IMPROVEMENTS, false)
                            sharedPreferencesEditor.apply()
                        }
                    }
                }

                if (sharedPreferences.getBoolean(Constants.SHOULD_CREATE_MILESTONES, true)) {
                    runBlocking {
                        launch {
                            val milestoneResult = baseViewModel.createMilestones()
                            println(milestoneResult)
                            val sharedPreferencesEditor = sharedPreferences.edit()
                            sharedPreferencesEditor.clear()
                            sharedPreferencesEditor.putBoolean(Constants.SHOULD_CREATE_MILESTONES, false)
                            sharedPreferencesEditor.apply()
                        }
                    }
                }

                startDestinationRoute = "registration_screen"
            }

            Surface(
                modifier = Modifier.fillMaxSize()
            ) {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            navigationIcon = {
                                if (!appScaffoldState.currentScreenRoute.equals("home_screen") &&
                                    !appScaffoldState.currentScreenRoute.equals("registration_screen")) {
                                    IconButton(onClick = { navController.navigateUp() }) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.arrow_back_24px),
                                            contentDescription = "")
                                    }
                                }
                            },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.secondaryContainer
                            ),
                            title = {
                                Text( text = appScaffoldState.topAppBarTitle.toString() )
                            },
                            actions = {
                                IconButton(
                                    onClick = {
                                        if (!appScaffoldState.currentScreenRoute.equals("settings_screen")) {
                                            navController.navigate("settings_screen")
                                        }
                                    }) {
                                    Icon(
                                        imageVector = Icons.Outlined.Settings,
                                        contentDescription = "Settings"
                                    )
                                }
                            }
                        )
                    },
                    bottomBar = {
                        BottomAppBar(
                            modifier = Modifier.height(80.dp),
                            containerColor = MaterialTheme.colorScheme.secondaryContainer,
                            actions = {
                                Row(
                                    horizontalArrangement = Arrangement.SpaceEvenly,
                                    modifier = Modifier
                                        .padding(top = 0.dp)
                                        .fillMaxWidth()
                                ) {
                                    IconButton(onClick = { navController.navigate("home_screen") }) {
                                        Icon(Icons.Outlined.Info, contentDescription = "Home")
                                    }
                                    IconButton(onClick = { navController.navigate("home_screen") }) {
                                        Icon(Icons.Outlined.Info, contentDescription = "Home")
                                    }
                                    IconButton(onClick = { navController.navigate("profile_screen")}) {
                                        Icon(Icons.Outlined.AccountCircle, contentDescription = "Profile")
                                    }
                                }
                            }
                        )
                    }
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = startDestinationRoute,
                        modifier = Modifier.padding(it)
                    ) {
                        composable(
                            "registration_screen"
                        ) {
                            RegistrationScreen(navController, sharedPreferences)
                            appScaffoldState = AppScaffoldState("Registration", "registration_screen")
                        }
                        composable(
                            "home_screen"
                        ) {
                            HomeScreen(navController)
                            appScaffoldState = AppScaffoldState("Alcohol Tracker", "home_screen")
                        }
                        composable(
                            "statistics_screen"
                        ) {
                            StatisticsScreen(navController)
                            appScaffoldState = AppScaffoldState("Statistics", "statistics_screen")
                        }
                        composable(
                            "health_improvements_screen/{daysPassed}"
                        ) {
                            HealthImprovementsScreen(navController)
                            appScaffoldState = AppScaffoldState("Health Improvements", "health_improvements_screen/{daysPassed}")
                        }
                        composable(
                            "health_improvement_details_screen/{healthImprovementId}/{daysPassed}"
                        ) {
                            HealthImprovementsDetailsScreen(navController)
                            appScaffoldState = AppScaffoldState("Health Improvement details", "health_improvement_details_screen/{healthImprovementId}/{daysPassed}")
                        }
                        composable(
                            "settings_screen"
                        ) {
                            SettingsScreen(baseViewModel)
                            appScaffoldState = AppScaffoldState("Settings", "settings_screen")
                        }
                        composable(
                            route = "milestones_screen"
                        ) {
                            MilestonesScreen(navController)
                            appScaffoldState = AppScaffoldState("Milestones", "milestones_screen")
                        }
                        composable(
                            route = "profile_screen"
                        ) {
                            ProfileScreen(navController)
                            appScaffoldState = AppScaffoldState("Profile", "profile_screen")
                        }
                    }
                }
            }
        }
    }
}