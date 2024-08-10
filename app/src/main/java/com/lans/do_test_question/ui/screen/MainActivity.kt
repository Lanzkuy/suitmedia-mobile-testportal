package com.lans.do_test_question.ui.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lans.do_test_question.model.User
import com.lans.do_test_question.ui.route.Route
import com.lans.do_test_question.ui.screen.first.FirstScreen
import com.lans.do_test_question.ui.screen.second.SecondScreen
import com.lans.do_test_question.ui.screen.third.ThirdScreen
import com.lans.do_test_question.ui.theme.DoTestQuestionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            DoTestQuestionTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    var user = User()
                    var username = ""
                    NavHost(
                        navController = navController,
                        route = Route.RouteName,
                        startDestination = Route.FirstScreen
                    ) {
                        composable(Route.FirstScreen) {
                            FirstScreen(
                                navigateToSecondScreen = {
                                    username = it
                                    user = User()
                                    navController.navigate(Route.SecondScreen)
                                }
                            )
                        }
                        composable(Route.SecondScreen) {
                            SecondScreen(
                                user = user,
                                username = username,
                                navigateToThirdScreen = {
                                    navController.navigate(Route.ThirdScreen)
                                },
                                navigateBack = {
                                    navController.navigateUp()
                                }
                            )
                        }
                        composable(Route.ThirdScreen) {
                            ThirdScreen(
                                navigateBack = {
                                    if (it != null) {
                                        user = it
                                    }
                                    navController.navigateUp()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}